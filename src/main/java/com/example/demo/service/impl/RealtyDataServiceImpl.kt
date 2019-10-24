package com.example.demo.service.impl

import com.example.demo.enumerate.SingleMapEnum
import com.example.demo.service.RealtyDataService
import com.geccocrawler.gecco.GeccoEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.geccocrawler.gecco.pipeline.PipelineFactory



/**
 * @Description 房地产数据接口实现
 * @Author mengqinghao
 * @Date 2018/11/19 3:38 PM
 * @Version 1.0
 */
@Service
class RealtyDataServiceImpl : RealtyDataService {

    private val LOGGER: Logger = LoggerFactory.getLogger(RealtyDataServiceImpl::class.java)

    @Autowired lateinit var springPipelineFactory: PipelineFactory

    /**
     * @Description: 爬取数据
     * @author mengqinghao
     * @param:
     * @date 2018/11/19 3:54 PM
     * @return:
     */
    override fun spiderData() {
        LOGGER.info("爬取数据-------------start")
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                // 工程的包路径
                .classpath("com.example.demo.crawler")
                // 开始抓取的页面地址
                .start("https://bj.lianjia.com/ershoufang/pg1/")
                // 开启几个爬虫线程
                .thread(10)
                // 单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //.loop(true) 是否循环抓取默认false
                //.mobile(false) 表示使用移动端还是pc端的UserAgent。默认为false使用pc端的UserAgent
//                .start() // start 非阻塞启动  run 阻塞启动
                .run()
        SingleMapEnum.SINGLE_DEMO.map.clear()
        LOGGER.info("爬取数据-------------end")
    }

}