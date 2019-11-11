package com.example.demo.crawler.lottery

import com.alibaba.fastjson.JSON
import com.geccocrawler.gecco.pipeline.Pipeline
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 * @Description: 处理中奖记录爬虫
 * @Author MengQingHao
 * @Date 2019/11/11 6:29 下午
 * @Version 1.0
 */
@Service
class HandleLottery : Pipeline<LotteryInfo> {

    private val logger: Logger = LoggerFactory.getLogger(HandleLottery::class.java)

    /**
     * @Description: 处理爬取内容
     * @author MengQingHao
     * @date 2019/11/11 6:32 下午
     * @version 1.0
     */
    override fun process(lotteryInfo: LotteryInfo?) {
        logger.info("处理----------------》开始")
        logger.info(JSON.toJSONString(lotteryInfo))
    }
}