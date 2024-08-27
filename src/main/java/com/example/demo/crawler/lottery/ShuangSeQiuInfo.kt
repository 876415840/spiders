package com.example.demo.crawler.lottery

import com.geccocrawler.gecco.annotation.Gecco
import com.geccocrawler.gecco.annotation.HtmlField
import com.geccocrawler.gecco.annotation.Request
import com.geccocrawler.gecco.request.HttpRequest
import com.geccocrawler.gecco.spider.HtmlBean

/**
 * @Description: 彩票信息
 * @Author MengQingHao
 * @Date 2019/11/11 2:05 下午
 * @Version 1.0
 */
@Gecco(pipelines = ["handleShuangSeQiuInfo"])
class ShuangSeQiuInfo : HtmlBean {

    // 向指定URL发送GET请求
    @Request
    var request : HttpRequest? = null

    @HtmlField(cssPath = "span.iSelectBox > div.iSelectList > a")
    var periodList : List<PeriodInfo>? = null

    @HtmlField(cssPath = "span.iSelectBox > a#change_date")
    var currentPeriod : String? = null

    @HtmlField(cssPath = "div.ball_box01 > ul > li.ball_blue")
    var blueBall : String? = null

    @HtmlField(cssPath = "table.kj_tablelist02 td")
    var trList : List<String>? = null

}