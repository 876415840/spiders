package com.example.demo.crawler.lottery

import com.example.demo.entity.ShuangSeQiu
import com.example.demo.enumerate.SingleMapEnum
import com.example.demo.mapper.ShuangSeQiuMapper
import com.geccocrawler.gecco.pipeline.Pipeline
import com.geccocrawler.gecco.scheduler.DeriveSchedulerContext
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
@Service("handleShuangSeQiu")
class HandleShuangSeQiu : Pipeline<ShuangSeQiuInfo> {

    private val logger: Logger = LoggerFactory.getLogger(HandleShuangSeQiu::class.java)

    @Autowired
    private
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    /**
     * @Description: 处理爬取内容
     * @author MengQingHao
     * @date 2019/11/11 6:32 下午
     * @version 1.0
     */
    override fun process(shuangSeQiuInfo: ShuangSeQiuInfo?) {
        val maxPeriod = shuangSeQiuMapper.getMaxPeriod()
        var prefix = maxPeriod.toString().substring(0, 2)
        var nowMaxPeriod = shuangSeQiuInfo!!.periodList!![0].period
        if (Integer.valueOf(prefix + nowMaxPeriod) == maxPeriod) {
            return
        }

        var currentPeriod = Integer.valueOf(prefix + shuangSeQiuInfo.currentPeriod)
        if (currentPeriod > maxPeriod) {
            var success = false
            for (i in shuangSeQiuInfo.trList!!.indices){
                var str = shuangSeQiuInfo.trList!![i]
                var redBallArr = str.split(" ")
                if (redBallArr.size == 6) {
                    var shuangSeQiu = ShuangSeQiu(currentPeriod, redBallArr[0], redBallArr[1], redBallArr[2], redBallArr[3], redBallArr[4], redBallArr[5], shuangSeQiuInfo.blueBall)
                    shuangSeQiuMapper.save(shuangSeQiu)
                    logger.info("--------爬取{}期---红球{}---篮球{}", currentPeriod, redBallArr, shuangSeQiuInfo.blueBall)
                    // 清除失败次数
                    if (SingleMapEnum.SINGLE_DEMO.lotteryFailed.size > 0) {
                        SingleMapEnum.SINGLE_DEMO.lotteryFailed.clear()
                    }
                    success = true
                }
            }
            if (!success) {
                SingleMapEnum.SINGLE_DEMO.lotteryFailed.add(currentPeriod)
            }
        }

        // 跳转下一期
        skipNext(shuangSeQiuInfo, prefix, maxPeriod)
    }

    private fun skipNext(shuangSeQiuInfo: ShuangSeQiuInfo?, prefix: String, maxPeriod: Int) {
        var periodList = shuangSeQiuInfo!!.periodList!!
        var index = periodList.size
        var failedNum = 0
        while (index > 0) {
            var periodInfo = periodList[index - 1]
            if (Integer.valueOf(prefix + periodInfo.period) > maxPeriod) {
                if (failedNum == SingleMapEnum.SINGLE_DEMO.lotteryFailed.size) {
                    DeriveSchedulerContext.into(shuangSeQiuInfo.request!!.subRequest(StringUtils.join("http://kaijiang.500.com/shtml/ssq/", periodInfo.period, ".shtml")))
                    break
                }
                failedNum++
            }
            index--
        }
    }


}