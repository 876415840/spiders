package com.example.demo.service.impl

import com.example.demo.mapper.HouseInfoMapper
import com.example.demo.mapper.ShuangSeQiuMapper
import com.example.demo.service.RealtyDataService
import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.pipeline.PipelineFactory
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 爬取双色球中奖记录
 * @Author MengQingHao
 * @Date 2019/11/11 6:36 下午
 * @Version 1.0
 */
@Service
class LotteryDataServiceImpl : RealtyDataService {

    private val logger: Logger = LoggerFactory.getLogger(LotteryDataServiceImpl::class.java)

    @Autowired
    lateinit var springPipelineFactory: PipelineFactory

    @Autowired
    private
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    override fun spiderData() {
        logger.info("爬取双色球数据-------------start")

//        val maxPeriod = shuangSeQiuMapper.getMaxPeriod()
//        runSpider(maxPeriod.toString().substring(2))

        logger.info("爬取双色球数据-------------end")
    }

    /**
     * 爬取数据
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:57 AM
     * @return
     */
    private fun runSpider(start: String) {
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                // 工程的包路径
                .classpath("com.example.demo.crawler.lottery")
                // 开始抓取的页面地址
                .start(StringUtils.join("http://kaijiang.500.com/shtml/ssq/", start, ".shtml"))
                // 开启几个爬虫线程
                .thread(10)
                // 单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //.loop(true) 是否循环抓取默认false
                //.mobile(false) 表示使用移动端还是pc端的UserAgent。默认为false使用pc端的UserAgent
                //                .start() // start 非阻塞启动  run 阻塞启动
                .run()
    }
}