package com.example.demo.crawler.lottery

import com.geccocrawler.gecco.annotation.Attr
import com.geccocrawler.gecco.annotation.HtmlField
import com.geccocrawler.gecco.spider.HtmlBean

/**
 * @Description: 期数信息
 * @Author MengQingHao
 * @Date 2019/11/11 6:25 下午
 * @Version 1.0
 */
class PeriodInfo : HtmlBean {

    @HtmlField(cssPath = "a")
    var period : String? = null

    @Attr("href")
    @HtmlField(cssPath = "a")
    var href : String? = null
}