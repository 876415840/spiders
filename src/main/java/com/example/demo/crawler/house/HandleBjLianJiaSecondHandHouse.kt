package com.example.demo.crawler.house

import com.alibaba.fastjson.JSON
import com.example.demo.entity.HouseInfo
import com.example.demo.entity.PriceChange
import com.example.demo.enumerate.SingleMapEnum
import com.example.demo.mapper.HouseInfoMapper
import com.example.demo.mapper.PriceChangeMapper
import com.example.demo.vo.PriceChangeVO
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.SchedulerContext
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/12/19 6:08 PM
 * @Version 1.0
 */
@Service("bjLianJiaSecondHandHouse")
class HandleBjLianJiaSecondHandHouse : Pipeline<BjLianJiaSecondHandHouse> {

    private val logger: Logger = LoggerFactory.getLogger(HandleBjLianJiaSecondHandHouse::class.java)

    @Autowired
    private
    lateinit var houseInfoMapper: HouseInfoMapper

    @Autowired
    private
    lateinit var priceChangeMapper: PriceChangeMapper

    /**
     * @Description: 处理抓取内容
     * @author mengqinghao
     * @param:
     * @date 2018/12/19 6:10 PM
     * @return:
     */
    override fun process(secondHandHouse: BjLianJiaSecondHandHouse?) {
        var houseInfoList : List<SecondHandHouseInfo>? = secondHandHouse!!.houseInfoList
        if(CollectionUtils.isNotEmpty(houseInfoList)){
            var houseInfoByCode = SingleMapEnum.SINGLE_DEMO.houseInfoByCode
            var exceptions = SingleMapEnum.SINGLE_DEMO.exceptions
            var priceChanges = SingleMapEnum.SINGLE_DEMO.priceChanges
            try {
                for (i in houseInfoList!!.indices){
                    var secondHandHouseInfo : SecondHandHouseInfo = houseInfoList[i]
                    var houseCode : String? = secondHandHouseInfo.houseCode
                    if(StringUtils.isBlank(houseCode)){
                        logger.warn("=------------------------------houseCode is null--data:{}", JSON.toJSONString(secondHandHouse))
                        continue
                    }
                    var someHouseInfo : HouseInfo? = houseInfoByCode[houseCode!!]
                    if (someHouseInfo != null) {
                        logger.warn("-----------------重复-------------------houseCode:{}", houseCode)
                        continue
                    }
                    var houseInfo = getHouseInfo(secondHandHouseInfo)
                    houseInfoByCode[houseCode!!] = houseInfo
                    var oldHouseInfo = houseInfoMapper.getByCode(houseCode!!)
                    if(oldHouseInfo != null){
                        // 价格变化
                        if (houseInfo.totalPrice != oldHouseInfo.totalPrice){
                            var priceChangeVO = PriceChangeVO(houseInfo.area, houseInfo.housingEstate, houseCode!!, houseInfo.totalPrice, houseInfo.unitPrice, oldHouseInfo.totalPrice, oldHouseInfo.unitPrice)
                            priceChanges.add(priceChangeVO)
                            logger.info("-----------价格有变化==--  {}", JSON.toJSONString(priceChangeVO))
                            var now = Date()
                            var priceChange = PriceChange()
                            priceChange.houseCode = oldHouseInfo.code
                            priceChange.totalPrice = oldHouseInfo.totalPrice
                            priceChange.unitPrice = oldHouseInfo.unitPrice
                            priceChange.createTime = now
                            priceChange.updateTime = now
                            priceChangeMapper.save(priceChange)
                        }
                        houseInfoMapper.updateByCode(houseInfo)
                    }else{
                        houseInfo.createTime = houseInfo.updateTime
                        houseInfoMapper.save(houseInfo)
                    }
                }
            } catch (e: Exception) {
                exceptions.add(e.message!!)
                logger.error("处理页面数据异常", e)
            }
            try {
                var pageData: String? = secondHandHouse!!.pageData
                // {"totalPage":100,"curPage":99}
                if (pageData != null) {
                    var obj = JSON.parseObject(pageData)
                    var curPage = obj.getIntValue("curPage")
                    logger.info("爬取数据--------curPage-----{}页", curPage)
                    if (curPage < obj.getIntValue("totalPage")) {
                        SchedulerContext.into(secondHandHouse!!.request!!.subRequest("https://bj.lianjia.com/ershoufang/pg" + (curPage + 1) + "/"))
                    }
                } else {
                    var url : String = secondHandHouse!!.request!!.url
                    var pgIndex: Int = StringUtils.indexOf(url,"/pg")
                    var indexSuffix: String = StringUtils.substring(url,pgIndex+3)
                    var index: String = StringUtils.substring(indexSuffix,0,StringUtils.indexOf(indexSuffix,"/"))
                    var page: Int = Integer.valueOf(index)+1
                    url = StringUtils.replace(url,"/pg${index}/","/pg${page}/")
                    SchedulerContext.into(secondHandHouse!!.request!!.subRequest(url))
                    logger.info("爬取数据--------index-----{}页", index)
                }
            } catch (e: Exception) {
                exceptions.add(e.message!!)
                logger.error("跳转页面异常", e)
            }
        }
    }

    /**
     * 解析房屋信息
     *
     * houseInfo = <span class="houseIcon"></span>2室1厅 | 47.32平米 | 南 北 | 精装 | 中楼层(共12层) | | 板塔结合
     *
     * @author mengqinghao 
     * @param houseHtmlInfo
     * @date 2019/1/29 4:05 PM
     * @return
     */
    private fun getHouseInfo(houseHtmlInfo: SecondHandHouseInfo): HouseInfo {
        var houseInfo = HouseInfo()
        houseInfo.updateTime = Date()
        houseInfo.code = houseHtmlInfo.houseCode
        houseInfo.title = houseHtmlInfo.title
        houseInfo.type = houseHtmlInfo.type
        // 价格信息-从文本中截取
        var totalPrice = houseHtmlInfo.totalPrice!!.replace("<span>", "").replace("</span>万", "")
        // 单位(元)
        houseInfo.totalPrice = BigDecimal(10000).multiply(BigDecimal(totalPrice)).toInt()
        var unitPrice = houseHtmlInfo.unitPrice!!.replace("单价", "").replace("元/平米", "")
        houseInfo.unitPrice = BigDecimal(unitPrice).toInt()
        // ["VR房源","房本满五年","随时看房"]
        houseInfo.vr = 0
        houseInfo.taxfree = 0
        houseInfo.anyTime = 0
        houseInfo.subway = 0
        for (i in houseHtmlInfo.tag!!.indices){
            var tag = houseHtmlInfo.tag!![i]
            if("vr".equals(tag)){
                houseInfo.vr = 1
            }else if("taxfree".equals(tag)){
                houseInfo.taxfree = 1
            }else if("haskey".equals(tag)){
                houseInfo.anyTime = 1
            }else if("subway".equals(tag)){
                houseInfo.subway = 1
            }
        }
        // 解析规格信息
        var infos = houseHtmlInfo.houseInfo!!.split("|")
        var size = infos.size
        if (size >= 1) {
            var spec = infos[0].split("</span>")
            if (spec.size >= 2) {
                houseInfo.specification = spec[1].trim()
            }
        }
        if (size >= 2) {
            var houseSize = infos[1].trim().replace("平米", "")
            // 平方分米
            try {
                houseInfo.size = BigDecimal(houseSize).multiply(BigDecimal(100)).toInt()
            } catch (e: Exception) {
                houseInfo.size = -1
            }
        }
        if (size >= 3) {
            houseInfo.orientation = infos[2].trim()
        }
        if (size >= 4) {
            houseInfo.decorationType = infos[3].trim()
        }
        if (size >= 5) {
            houseInfo.storeyType = infos[4].trim()
        }
        if (size >= 6) {
            var towerInfo = infos[5].trim()
            if (StringUtils.isNotEmpty(towerInfo)) {
                houseInfo.year = towerInfo.replace("年建", "").trim().toInt()
            }
        }
        if (size >= 7) {
            houseInfo.towerType = infos[6].trim()
        }
        // 房屋位置属性
        if (houseHtmlInfo.positionInfo!!.size >= 2) {
            houseInfo.housingEstate = houseHtmlInfo.positionInfo!![0].trim()
            houseInfo.area = houseHtmlInfo.positionInfo!![1].trim()
        }
        return houseInfo
    }

}