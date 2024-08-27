package com.example.demo.service.impl

import com.example.demo.mapper.ShuangSeQiuMapper
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author qinghao.meng created on 2024/8/27
 * @version $Id$
 */
@Service("shuangSeQiuLotteryDataService")
class ShuangSeQiuLotteryDataServiceImpl : AbstractLotteryDataService() {

    private val logger: Logger = LoggerFactory.getLogger(ShuangSeQiuLotteryDataServiceImpl::class.java)

    private var index = 0

    @Autowired
    private lateinit var shuangSeQiuMapper: ShuangSeQiuMapper


    override fun getIndex(): Int {
        return index
    }

    override fun spiderData() {
        index++
        logger.info("爬取双色球数据-------------start")

        try {
            val maxPeriod = shuangSeQiuMapper.getMaxPeriod()
            val url = StringUtils.join("http://kaijiang.500.com/shtml/ssq/", maxPeriod.toString().substring(2), ".shtml")
            runSpider(url)
        } catch (e: Exception) {
            logger.info("爬取双色球异常", e)
        }

        logger.info("爬取双色球数据-------------end")
    }

}