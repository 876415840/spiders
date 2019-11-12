package com.example.demo.crawler.lottery

import com.alibaba.fastjson.JSON
import com.example.demo.entity.ShuangSeQiu
import com.example.demo.mapper.ShuangSeQiuMapper
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.SchedulerContext
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 处理中奖记录爬虫
 * @Author MengQingHao
 * @Date 2019/11/11 6:29 下午
 * @Version 1.0
 */
@Service("handleLottery")
class HandleLottery : Pipeline<LotteryInfo> {

    private val logger: Logger = LoggerFactory.getLogger(HandleLottery::class.java)

    @Autowired
    private
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    /**
     * @Description: 处理爬取内容
     * @author MengQingHao
     * @date 2019/11/11 6:32 下午
     * @version 1.0
     */
    override fun process(lotteryInfo: LotteryInfo?) {
        val maxPeriod = shuangSeQiuMapper.getMaxPeriod()
        var prefix = maxPeriod.toString().substring(0, 2)
        var nowMaxPeriod = lotteryInfo!!.periodList!![0].period
        if (Integer.valueOf(prefix + nowMaxPeriod) == maxPeriod) {
            return
        }

        var currentPeriod = Integer.valueOf(prefix + lotteryInfo!!.currentPeriod)
        if (currentPeriod > maxPeriod) {
            if (lotteryInfo!!.trList!!.size == 2) {
                var strs = lotteryInfo!!.trList!![1].split("</td>")
                if (strs.size >= 2) {
                    var redBalls = strs[1].replace("<td>", "").trim()
                    var redBallArr = redBalls.split(" ")
                    if (redBallArr.size == 6) {
                        var shuangSeQiu = ShuangSeQiu(currentPeriod, redBallArr[0], redBallArr[1], redBallArr[2], redBallArr[3], redBallArr[4], redBallArr[5], lotteryInfo!!.blueBall)
                        shuangSeQiuMapper.save(shuangSeQiu)
                        logger.info("--------爬取{}期---红球{}---篮球{}", currentPeriod, redBallArr, lotteryInfo!!.blueBall)
                    }
                }
            }
        }

        // 跳转下一期
        skipNext(lotteryInfo, prefix, maxPeriod)
    }

    private fun skipNext(lotteryInfo: LotteryInfo?, prefix: String, maxPeriod: Int) {
        var periodList = lotteryInfo!!.periodList!!
        var index = periodList.size
        while (index > 0) {
            var periodInfo = periodList[index - 1]
            if (Integer.valueOf(prefix + periodInfo.period) > maxPeriod) {
                SchedulerContext.into(lotteryInfo!!.request!!.subRequest(StringUtils.join("http://kaijiang.500.com/shtml/ssq/", periodInfo.period, ".shtml")))
                break
            }
            index--
        }
    }
}