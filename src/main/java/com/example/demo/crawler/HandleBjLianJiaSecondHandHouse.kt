package com.example.demo.crawler

import com.alibaba.fastjson.JSON
import com.example.demo.entity.HouseInfo
import com.example.demo.mapper.HouseInfoMapper
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.SchedulerContext
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
            for (i in houseInfoList!!.indices){
                var houseCode : String = houseInfoList[i]!!.houseCode!!
                if(StringUtils.isBlank(houseCode)){
                    throw RuntimeException()
                }
                var houseHtmlInfo = houseInfoList[i]
                var houseInfo = HouseInfo()
                houseInfo.code = houseHtmlInfo.houseCode
                houseInfo.title = houseHtmlInfo.title
                houseInfo.housingEstate = "小区"
                houseInfo.specification = "规格"
                houseInfo.size = 11 //平方分米
                houseInfo.orientation = "朝向"
                houseInfo.totalPrice = 333324 //元
                houseInfo.subway = 1
                houseInfo.vr = 1
                houseInfo.taxfree = 1
                houseInfo.anyTime = 1
                //todo mapper 不能注入  ERROR : kotlin.UninitializedPropertyAccessExceptionlateinit property houseInfoMapper has not been initialized
                //todo 其余数据
                houseInfoMapper.saveUser(houseInfo)
                println(JSON.toJSONString(houseInfoList[i]))
                break
            }
//            var url : String = secondHandHouse!!.request!!.url
//            var pgIndex: Int = StringUtils.indexOf(url,"/pg")
//            var indexSuffix: String = StringUtils.substring(url,pgIndex+3)
//            var index: String = StringUtils.substring(indexSuffix,0,StringUtils.indexOf(indexSuffix,"/"))
//            var page: Int = Integer.valueOf(index)+1
//            url = StringUtils.replace(url,"/pg${index}/","/pg${page}/")
//            SchedulerContext.into(secondHandHouse!!.request!!.subRequest(url))
        }
    }

}