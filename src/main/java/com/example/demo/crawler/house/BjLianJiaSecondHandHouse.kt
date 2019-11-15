package com.example.demo.crawler.house

import com.geccocrawler.gecco.annotation.Attr
import com.geccocrawler.gecco.annotation.Gecco
import com.geccocrawler.gecco.annotation.HtmlField
import com.geccocrawler.gecco.annotation.Request
import com.geccocrawler.gecco.request.HttpRequest
import com.geccocrawler.gecco.spider.HtmlBean

/**
 * @Description 北京链家二手房
 * @Author mengqinghao
 * @Date 2018/12/19 5:11 PM
 * @Version 1.0
 */
@Gecco(pipelines = ["handleBjLianJiaSecondHandHouse"])
class BjLianJiaSecondHandHouse : HtmlBean {

    // 向指定URL发送GET请求
    @Request
    var request : HttpRequest? = null

    @HtmlField(cssPath = "ul.sellListContent > li")
    var houseInfoList : List<SecondHandHouseInfo>? = null

    @Attr("page-data")
    @HtmlField(cssPath = "div.contentBottom > div.page-box > div.page-box")
    var pageData : String? = null
}