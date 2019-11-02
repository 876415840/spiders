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
import java.math.BigDecimal


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

        // 爬取数据
        runSpider()

        // 发送异常信息
        sendErrorMessage()

        // 发送价格变化通知
        sendPriceChangeMessage()

        // 清除全局容器
        clearContainer()

        logger.info("爬取数据-------------end")
    }

    /**
     * 发送价格变化通知
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:59 AM
     * @return
     */
    private fun sendPriceChangeMessage() {
        if (CollectionUtils.isNotEmpty(SingleMapEnum.SINGLE_DEMO.priceChanges)) {
            var upMesage = StringBuilder()
            var downMessage = StringBuilder()
            var up = 0
            var down = 0
            var upPrices = ArrayList<Int>()
            var downPrices = ArrayList<Int>()
            var upScale = ArrayList<Int>()
            var downScale = ArrayList<Int>()
            for (i in SingleMapEnum.SINGLE_DEMO.priceChanges!!.indices) {
                var priceChangeVO = SingleMapEnum.SINGLE_DEMO.priceChanges[i]
                if (priceChangeVO.oldTotalPrice!! != priceChangeVO.totalPrice!!) {
                    var risePrice: Boolean = priceChangeVO.oldTotalPrice!! < priceChangeVO.totalPrice!!
                    if (risePrice) {
                        var changePrice = priceChangeVO.totalPrice!! - priceChangeVO.oldTotalPrice!!
                        putMessage(upMesage, priceChangeVO, changePrice, risePrice)
                        upPrices.add(changePrice)
                        upScale.add(changePrice * 1000 / priceChangeVO.totalPrice!!)
                        up++
                    } else {
                        var changePrice = priceChangeVO.oldTotalPrice!! - priceChangeVO.totalPrice!!
                        putMessage(downMessage, priceChangeVO, changePrice, risePrice)
                        downPrices.add(changePrice)
                        downScale.add(changePrice * 1000 / priceChangeVO.oldTotalPrice!!)
                        down++
                    }
                }
            }
            var upDesc = getUpDesc(up, upPrices, upScale)
            var downDesc = getDownDesc(down, downPrices, downScale)
            emailUtil.sendTextEmail(toMail, StringUtils.join("北京二手房价格今天发生变化"), StringBuilder(upDesc).append(downDesc).append("\n\n\n").append(upMesage).append(downMessage).toString())
        }
    }

    private fun putMessage(upMesage: StringBuilder, priceChangeVO: PriceChangeVO, changePrice: Int, risePrice: Boolean) {
        var riseDesc = "下降"
        if (risePrice) {
            riseDesc = "上涨"
        }
        upMesage.append("地区：").append(priceChangeVO.area).append(", 小区：").append(priceChangeVO.housingEstate).append(", 编号：").append(priceChangeVO.houseCode)
                .append(", 总价：").append(priceChangeVO.oldTotalPrice!!).append(" -> ").append(priceChangeVO.totalPrice!!).append(" 单价：")
                .append(priceChangeVO.oldUnitPrice).append(" -> ").append(priceChangeVO.unitPrice).append("-------").append(riseDesc).append(changePrice).append("元---\n")
    }

    private fun getDownDesc(down: Int, downPrices: ArrayList<Int>, downScale: ArrayList<Int>): String {
        var downDesc = "下降0户\n"
        if (down > 0) {
            var maxPrice = downPrices.max()
            var maxScale = downScale.max()
            var averPrice = downPrices.average()
            var averScale = downScale.average()
            downDesc = StringBuilder("下降").append(down).append("套，")
                    .append("最大降幅：").append(BigDecimal.valueOf(maxScale!!.toLong()).divide(BigDecimal.TEN)).append("%，最大下降金额").append(maxPrice)
                    .append("，平均降幅：").append(BigDecimal.valueOf(averScale!!.toLong()).divide(BigDecimal.TEN)).append("%，平均下降金额：").append(averPrice)
                    .append(" --ps金额并不一定和幅度对应\n").toString()
        }
        return downDesc
    }

    private fun getUpDesc(up: Int, upPrices: ArrayList<Int>, upScale: ArrayList<Int>): String {
        var upDesc = "上涨0户\n"
        if (up > 0) {
            var maxPrice = upPrices.max()
            var maxScale = upScale.max()
            var averPrice = upPrices.average()
            var averScale = upScale.average()
            upDesc = StringBuilder("上涨").append(up).append("套，")
                    .append("最大涨幅：").append(BigDecimal.valueOf(maxScale!!.toLong()).divide(BigDecimal.TEN)).append("%，最大上涨金额").append(maxPrice)
                    .append("，平均涨幅：").append(BigDecimal.valueOf(averScale!!.toLong()).divide(BigDecimal.TEN)).append("%，平均上涨金额：").append(averPrice)
                    .append(" --ps金额并不一定和幅度对应\n").toString()
        }
        return upDesc
    }

    /**
     * 发送异常通知
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:58 AM
     * @return
     */
    private fun sendErrorMessage() {
        if (CollectionUtils.isNotEmpty(SingleMapEnum.SINGLE_DEMO.exceptions)) {
            var stringBuilder = StringBuilder()
            for (i in SingleMapEnum.SINGLE_DEMO.exceptions!!.indices) {
                stringBuilder.append(SingleMapEnum.SINGLE_DEMO.exceptions[i]).append("---\n")
            }
            emailUtil.sendTextEmail(toMail, "【宇宙第一帅】你的爬虫出异常了", stringBuilder.toString())
        }
    }

    /**
     * 爬取数据
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:57 AM
     * @return
     */
    private fun runSpider() {
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
    }

    /**
     * 清除容器
     *
     * @author MengQingHao
     * @param null
     * @date 2019/10/31 11:56 AM
     * @return
     */
    private fun clearContainer() {
        SingleMapEnum.SINGLE_DEMO.houseInfoByCode.clear()
        SingleMapEnum.SINGLE_DEMO.exceptions.clear()
        SingleMapEnum.SINGLE_DEMO.priceChanges.clear()
    }

}