package com.example.demo.service.impl

import com.example.demo.enumerate.SingleMapEnum
import com.example.demo.service.RealtyDataService
import com.example.demo.util.EmailUtil
import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.pipeline.PipelineFactory
import org.apache.commons.collections.CollectionUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * @Description 房地产数据接口实现
 * @Author mengqinghao
 * @Date 2018/11/19 3:38 PM
 * @Version 1.0
 */
@Service
class RealtyDataServiceImpl : RealtyDataService {

    private val logger: Logger = LoggerFactory.getLogger(RealtyDataServiceImpl::class.java)

    @Autowired lateinit var springPipelineFactory: PipelineFactory

    @Autowired
    private
    lateinit var emailUtil: EmailUtil

    /**
     * @Description: 爬取数据
     * @author mengqinghao
     * @param:
     * @date 2018/11/19 3:54 PM
     * @return:
     */
    override fun spiderData() {
        logger.info("爬取数据-------------start")
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

        if (CollectionUtils.isNotEmpty(SingleMapEnum.SINGLE_DEMO.exceptions)) {
            var stringBuilder = StringBuilder()
            for (i in SingleMapEnum.SINGLE_DEMO.exceptions!!.indices) {
                stringBuilder.append(SingleMapEnum.SINGLE_DEMO.exceptions[i]).append("---\n")
            }
            emailUtil.sendTextEmail("mqhbeijing@163.com", "主题：爬虫异常", stringBuilder.toString())
        }

        if (CollectionUtils.isNotEmpty(SingleMapEnum.SINGLE_DEMO.priceChanges)) {
            var stringBuilder = StringBuilder()
            for (i in SingleMapEnum.SINGLE_DEMO.priceChanges!!.indices) {
                stringBuilder.append(SingleMapEnum.SINGLE_DEMO.priceChanges[i]).append("---\n")
            }
            //todo  计算变化
            emailUtil.sendTextEmail("mqhbeijing@163.com", "主题：二手房价格变化", stringBuilder.toString())
        }

        SingleMapEnum.SINGLE_DEMO.houseInfoByCode.clear()
        SingleMapEnum.SINGLE_DEMO.exceptions.clear()
        SingleMapEnum.SINGLE_DEMO.priceChanges.clear()
        logger.info("爬取数据-------------end")
    }

}