package com.example.demo.crawler.transaction

import com.alibaba.fastjson.JSON
import com.example.demo.crawler.house.BjLianJiaSecondHandHouse
import com.example.demo.entity.TransactionInfo
import com.example.demo.mapper.TransactionInfoMapper
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*

/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/12/19 6:08 PM
 * @Version 1.0
 */
@Service("handleHouseTransactionInfo")
class HandleHouseTransactionInfo : Pipeline<HouseTransactionInfo> {

    private val logger: Logger = LoggerFactory.getLogger(HandleHouseTransactionInfo::class.java)

    @Autowired
    private
    lateinit var transactionInfoMapper: TransactionInfoMapper

    /**
     * @Description: 处理抓取内容
     * @author mengqinghao
     * @param:
     * @date 2018/12/19 6:10 PM
     * @return:
     */
    override fun process(houseTransactionInfo: HouseTransactionInfo?) {
        val houseCode =  houseTransactionInfo!!.houseCode;
        if (StringUtils.isBlank(houseCode)) {
            logger.warn("---------没有找到内容-------应该是未成交")
            return
        }
        try {
            val now = Date()
            val transactionInfo =  TransactionInfo()
            transactionInfo.createTime = now
            transactionInfo.updateTime = now
            transactionInfo.code = houseCode
            transactionInfo.transactionDate = houseTransactionInfo.transactionDate
            transactionInfo.dealPrice = houseTransactionInfo.dealTotalPrice
            for (i in houseTransactionInfo.msg!!.indices) {
                val msg = houseTransactionInfo.msg!![i]
                val arry = msg.split("</label>")
                if (arry.size == 2) {
                    val str = arry[1]
                    val labelVal = arry[0].replace("<label>", "").trim()
                    if (str == "挂牌价格（万）") {
                        transactionInfo.stickerPrice = BigDecimal(10000).multiply(BigDecimal(labelVal)).toInt()
                    } else if (str == "成交周期（天）") {
                        transactionInfo.transactionPeriod = labelVal.toInt()
                    } else if (str == "调价（次）") {
                        transactionInfo.adjustCount = labelVal.toInt()
                    } else if (str == "带看（次）") {
                        transactionInfo.lookCount = labelVal.toInt()
                    } else if (str == "关注（人）") {
                        transactionInfo.follow = labelVal.toInt()
                    }
                }
            }
            transactionInfoMapper.save(transactionInfo)
        } catch (e: Exception) {
            logger.error("保存交易信息异常 - code:{}", houseCode, e)
        }
    }



}