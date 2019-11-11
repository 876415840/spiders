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
@Gecco(pipelines = ["handleLottery"])
class LotteryInfo : HtmlBean {

    // 向指定URL发送GET请求
    @Request
    var request : HttpRequest? = null

    @HtmlField(cssPath = "span.iSelectBox > div.listOverFlow > a")
    var periodList : List<PeriodInfo>? = null
}