package com.example.demo.service.impl

import com.example.demo.service.RealtyDataService
import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.pipeline.PipelineFactory
import org.springframework.beans.factory.annotation.Autowired

/**
 * @Description: 爬取彩票中奖记录
 * @Author MengQingHao
 * @Date 2019/11/11 6:36 下午
 * @Version 1.0
 */
abstract class AbstractLotteryDataService : RealtyDataService {

    @Autowired
    private lateinit var springPipelineFactory: PipelineFactory

    /**
     * 爬取数据
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:57 AM
     * @return
     */
    protected fun runSpider(url: String) {
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                // 工程的包路径
                .classpath("com.example.demo.crawler.lottery")
                // 开始抓取的页面地址
                .start(url)
                // 开启几个爬虫线程
                .thread(2)
                // 单个爬虫每次抓取完一个请求后的间隔时间
                .interval(1)
                //.loop(true) 是否循环抓取默认false
                //.mobile(false) 表示使用移动端还是pc端的UserAgent。默认为false使用pc端的UserAgent
                //                .start() // start 非阻塞启动  run 阻塞启动
                .run()
    }
}