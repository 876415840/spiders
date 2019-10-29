package com.example.demo.service.impl

import com.example.demo.enumerate.SingleMapEnum
import com.example.demo.service.RealtyDataService
import com.example.demo.util.EmailUtil
import com.example.demo.vo.PriceChangeVO
import com.geccocrawler.gecco.GeccoEngine
import com.geccocrawler.gecco.pipeline.PipelineFactory
import org.apache.commons.collections.CollectionUtils
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
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

    @Value("\${spring.mail.to-mail}")
    private val toMail: String? = null

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
            emailUtil.sendTextEmail(toMail, "【宇宙第一帅】你的爬虫出异常了", stringBuilder.toString())
        }

        if (CollectionUtils.isNotEmpty(SingleMapEnum.SINGLE_DEMO.priceChanges)) {
            var stringBuilder = StringBuilder()
            var up = 0
            var down = 0
            for (i in SingleMapEnum.SINGLE_DEMO.priceChanges!!.indices) {
                var priceChangeVO = SingleMapEnum.SINGLE_DEMO.priceChanges[i]
                if (priceChangeVO.oldTotalPrice!! != priceChangeVO.totalPrice!!) {
                    var risePrice: Boolean = priceChangeVO.oldTotalPrice!! < priceChangeVO.totalPrice!!
                    var riseDesc: String?
                    if (risePrice) {
                        riseDesc = "----------------------------上涨"
                        up++
                    } else {
                        riseDesc = "----------------------------下降"
                        down++
                    }
                    stringBuilder.append("地区：").append(priceChangeVO.area).append(", 小区：").append(priceChangeVO.housingEstate).append(", 编号：").append(priceChangeVO.houseCode)
                            .append(", 总价：").append(priceChangeVO.oldTotalPrice!!).append(" -> ").append(priceChangeVO.totalPrice!!).append(" 单价：")
                            .append(priceChangeVO.oldUnitPrice).append(" -> ").append(priceChangeVO.unitPrice).append(riseDesc).append("---\n")
                }
            }
            emailUtil.sendTextEmail(toMail, StringUtils.join("北京二手房价格今天发生变化，上涨", up, "户，下降", down, "户"), stringBuilder.toString())
        }
        // 清除全局容器
        SingleMapEnum.SINGLE_DEMO.houseInfoByCode.clear()
        SingleMapEnum.SINGLE_DEMO.exceptions.clear()
        SingleMapEnum.SINGLE_DEMO.priceChanges.clear()
        logger.info("爬取数据-------------end")
    }

}