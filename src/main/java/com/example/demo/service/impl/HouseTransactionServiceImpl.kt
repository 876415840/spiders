package com.example.demo.service.impl

import com.example.demo.mapper.TransactionInfoMapper
import com.example.demo.service.RealtyDataService
import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.pipeline.PipelineFactory
import com.geccocrawler.gecco.request.HttpGetRequest
import com.google.common.collect.Lists
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 房子成交数据接口实现
 * @Author MengQingHao
 * @Date 2020/7/2 2:53 下午
 */
@Service("houseTransactionService")
class HouseTransactionServiceImpl: RealtyDataService {

    private val logger: Logger = LoggerFactory.getLogger(HouseTransactionServiceImpl::class.java)

    @Autowired
    lateinit var springPipelineFactory: PipelineFactory

    @Autowired
    private
    lateinit var transactionInfoMapper: TransactionInfoMapper

    override fun getIndex(): Int {
        return 0;
    }

    override fun spiderData() {
        logger.info("爬取二手房交易数据-------------start");
        val codes = transactionInfoMapper.getTransactionCodes()

        val requests = Lists.newArrayListWithCapacity<HttpGetRequest>(codes.size);
        for (code in codes) {
            requests.add(HttpGetRequest("https://bj.lianjia.com/chengjiao/$code.html"))
        }
        runSpider(requests)
        logger.info("爬取二手房交易数据-------------end");
    }

    /**
     * 爬取数据
     * @param code
     * @return
     * @author MengQingHao
     * @date 2020/7/2 4:04 下午
     */
    private fun runSpider(requests: List<HttpGetRequest>) {
        GeccoEngine.create()
                .pipelineFactory(springPipelineFactory)
                // 工程的包路径
                .classpath("com.example.demo.crawler.transaction")
                // 开始抓取的页面地址
                .start(requests)
                // 开启几个爬虫线程
                .thread(20)
                // 单个爬虫每次抓取完一个请求后的间隔时间
//                .interval(2000)
                //.loop(true) 是否循环抓取默认false
                //.mobile(false) 表示使用移动端还是pc端的UserAgent。默认为false使用pc端的UserAgent
                //                .start() // start 非阻塞启动  run 阻塞启动
                .run()
    }
}