package com.example.demo.crawler

import com.geccocrawler.gecco.annotation.Attr
import com.geccocrawler.gecco.annotation.HtmlField
import com.geccocrawler.gecco.spider.HtmlBean

/**
 * @Description 二手房信息
 * @Author mengqinghao
 * @Date 2018/12/19 5:24 PM
 * @Version 1.0
 */
class SecondHandHouseInfo : HtmlBean {

    @HtmlField(cssPath = "div.title > a")
    var title : String? = null

    @Attr("data-housecode")
    @HtmlField(cssPath = "div.title > a")
    var houseCode : String? = null

    @HtmlField(cssPath = "div.title > span")
    var type : String? = null

    @HtmlField(cssPath = "div.address > div.houseInfo")
    var houseInfo : String? = null

    @HtmlField(cssPath = "div.flood > div.positionInfo")
    var positionInfo : String? = null

    @Attr("class")
    @HtmlField(cssPath = "div.followInfo > div.tag > span")
    var tag : List<String>? = null

    @HtmlField(cssPath = "div.followInfo > div.tag > span")
    var tagName : List<String>? = null

    @HtmlField(cssPath = "div.followInfo > div.priceInfo > div.totalPrice")
    var totalPrice : String? = null

    @HtmlField(cssPath = "div.followInfo > div.priceInfo > div.unitPrice > span")
    var unitPrice : String? = null
}