package com.example.demo.crawler

import com.example.demo.entity.HouseInfo
import com.example.demo.entity.PriceChange
import com.example.demo.mapper.HouseInfoMapper
import com.example.demo.mapper.PriceChangeMapper
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.SchedulerContext
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.StringUtils
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

    @Autowired
    lateinit var houseInfoMapper: HouseInfoMapper

    @Autowired
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
            try {
                for (i in houseInfoList!!.indices){
                    var houseCode : String = houseInfoList[i]!!.houseCode!!
                    if(StringUtils.isBlank(houseCode)){
                        throw RuntimeException()
                    }
                    var houseInfo = getHouseInfo(houseInfoList[i])
                    var oldHouseInfo = houseInfoMapper.getByCode(houseInfoList[i].houseCode!!)
                    if(oldHouseInfo != null){
                        houseInfoMapper.updateByCode(houseInfo)
                        if (!Objects.equals(houseInfo.totalPrice, oldHouseInfo.totalPrice) || Objects.equals(houseInfo.unitPrice, oldHouseInfo.unitPrice)){
                            var now = Date()
                            var priceChange = PriceChange()
                            priceChange.houseCode = oldHouseInfo.code
                            priceChange.totalPrice = oldHouseInfo.totalPrice
                            priceChange.unitPrice = oldHouseInfo.unitPrice
                            priceChange.createTime = now
                            priceChange.updateTime = now
                            priceChangeMapper.save(priceChange)
                        }
                    }else{
                        houseInfo.createTime = houseInfo.updateTime
                        houseInfoMapper.save(houseInfo)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            var url : String = secondHandHouse!!.request!!.url
            var pgIndex: Int = StringUtils.indexOf(url,"/pg")
            var indexSuffix: String = StringUtils.substring(url,pgIndex+3)
            var index: String = StringUtils.substring(indexSuffix,0,StringUtils.indexOf(indexSuffix,"/"))
            var page: Int = Integer.valueOf(index)+1
            url = StringUtils.replace(url,"/pg${index}/","/pg${page}/")
            SchedulerContext.into(secondHandHouse!!.request!!.subRequest(url))
        }
    }

    /**
     * 解析房屋信息
     *
     * {"houseCode":"101103955334",
     * "houseInfo":"<a href=\"https://bj.lianjia.com/xiaoqu/1111027375875/\" target=\"_blank\" data-log_index=\"1\" data-el=\"region\">佰嘉城 </a> \n<span class=\"divide\">/</span>3室1厅 \n<span class=\"divide\">/</span>117.97平米 \n<span class=\"divide\">/</span>南 西 北 \n<span class=\"divide\">/</span>简装 \n<span class=\"divide\">/</span>无电梯",
     * "positionInfo":"中楼层(共6层) \n<span class=\"divide\">/</span>2006年建板楼 \n<span class=\"divide\">/</span> \n<a href=\"https://bj.lianjia.com/ershoufang/huilongguan2/\" target=\"_blank\">回龙观</a>",
     * "tag":["vr","taxfree","haskey"],
     * "tagName":["VR房源","房本满五年","随时看房"],
     * "title":"中间楼层三居两卫，名厨明卫，满五年家庭名下首套房",
     * "totalPrice":"<span>458</span>万",
     * "type":"房主自荐",
     * "unitPrice":"单价38824元/平米"}
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
        var infos = houseHtmlInfo.houseInfo!!.split("</a>")
        if (infos.size == 2) {
            var housingEstate = infos[0].substring(infos[0].lastIndexOf(">") + 1)
            houseInfo.housingEstate = housingEstate.trim()
            var specs = infos[1].split("<span class=\"divide\">/</span>")
            var size = specs.size
            if (size >= 2) {
                houseInfo.specification = specs[1].trim()
            }
            if (size >= 3) {
                var houseSize = specs[2].trim().replace("平米", "")
                // 平方分米
                try {
                    houseInfo.size = BigDecimal(houseSize).multiply(BigDecimal(100)).toInt()
                } catch (e: Exception) {
                    houseInfo.size = -1
                }
            }
            if (size >= 4) {
                houseInfo.orientation = specs[3].trim()
            }
            if (size >= 5) {
                houseInfo.decorationType = specs[4].trim()
            }
            if (size >= 6) {
                var elevator = "有电梯".equals(specs[5].trim())
                if (elevator) {
                    houseInfo.elevator = 1
                } else {
                    houseInfo.elevator = 0
                }
            } else {
                houseInfo.elevator = 0
            }
        }
        // 房屋位置属性
        var positionInfos = houseHtmlInfo.positionInfo!!.split("<a")
        if(positionInfos.size == 2){
            // 楼层 年份 类型
            var positionInfoArr = positionInfos[0].split("<span class=\"divide\">/</span>")
            var size = positionInfoArr.size
            if(size >= 1){
                houseInfo.storeyType = positionInfoArr[0].trim()
            }
            if(size >= 2){
                var towerInfo = positionInfoArr[1].split("年建")
                if(towerInfo.size == 2){
                    houseInfo.year = Integer.valueOf(towerInfo[0].trim())
                    houseInfo.towerType = towerInfo[1].trim()
                }
            }
            houseInfo.area = positionInfos[1].substring(positionInfos[1].indexOf(">") + 1).replace("</a>","").trim()
        }
        return houseInfo
    }

}