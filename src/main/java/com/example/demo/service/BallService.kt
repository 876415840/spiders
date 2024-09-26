package com.example.demo.service

import com.example.demo.vo.BallCountVO

/**
 * @Description: 彩票相关处理
 * @Author MengQingHao
 * @Date 2019/11/15 11:36 上午
 * @Version 1.0
 */
interface BallService {

    /**
     * @Description: 获取球的概率
     * @author MengQingHao
     * @date 2019/11/15 12:05 下午
     * @version 1.0
     */
    fun setBallProbability(ballCounts: List<BallCountVO>) {
        var sum = ballCounts.sumOf { it.count!! }
        var size = ballCounts.size
        var probabilitySum = 0
        for (ballCount in ballCounts) {
            if (size == 1) {
                ballCount.count = 100 - probabilitySum
                break
            }
            var ballpProbability = ballCount.count!! * 100 / sum
            ballCount.count = ballpProbability
            probabilitySum += ballpProbability
            size--
        }
    }

    /**
     * @Description: 获取以往球的概率
     * @author MengQingHao
     * @date 2019/11/15 11:45 上午
     * @version 1.0
     */
    fun getBallProbability(): List<BallCountVO>

}