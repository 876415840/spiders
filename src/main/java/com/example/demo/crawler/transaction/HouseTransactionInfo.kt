package com.example.demo.crawler.transaction

import com.example.demo.crawler.house.SecondHandHouseInfo
import com.geccocrawler.gecco.annotation.Attr
import com.geccocrawler.gecco.annotation.Gecco
import com.geccocrawler.gecco.annotation.HtmlField
import com.geccocrawler.gecco.annotation.Request
import com.geccocrawler.gecco.request.HttpRequest
import com.geccocrawler.gecco.spider.HtmlBean

/**
 * @Description 二手房成交信息
 * @Author mengqinghao
 * @Date 2018/12/19 5:11 PM
 * @Version 1.0
 */
@Gecco(pipelines = ["handleHouseTransactionInfo"])
class HouseTransactionInfo : HtmlBean {

    // 向指定URL发送GET请求
    @Request
    var request : HttpRequest? = null

    @Attr("data-lj_action_resblock_id")
    @HtmlField(cssPath = "div.house-title")
    var houseCode : String? = null

    @HtmlField(cssPath = "div.house-title > div.wrapper > span")
    var transactionDate : String? = null

    @HtmlField(cssPath = "section.wrapper > div.overview > div.info > div.price > span.dealTotalPrice > i")
    var dealTotalPrice : String? = null

    @HtmlField(cssPath = "section.wrapper > div.overview > div.info > div.msg > span")
    var msg : List<String>? = null
}