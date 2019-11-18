package com.example.demo.service.impl

import com.alibaba.fastjson.JSON
import com.example.demo.service.BallService
import com.example.demo.service.LotteryService
import com.example.demo.vo.BallCountVO
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * @Description: 抽奖相关接口实现
 * @Author MengQingHao
 * @Date 2019/11/18 11:58 上午
 * @Version 1.0
 */
@Service
class LotteryServiceImpl:LotteryService {

    /**
     * 概率总数上线(分母)
     */
    private val PROBABILITY_SUM = 100

    /**
     * 放大倍数
     */
    private val MULTIPLE = 10000

    @Autowired
    lateinit var ballServices: Map<String, BallService>

    override fun getBalls(): String {
        // 抽红球
        var redList = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33)
        var redBallList = Lists.newArrayList<Int>()
        for (i in 1..6){
            var redBallProbability = ballServices["red$i"]?.getBallProbability()!!
            var redBall = getBall(redList, redBallProbability)
            redBallList.add(redBall)
            redList.remove(redBall)
        }
        redBallList.sort()

        // 抽篮球
        var blueList = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)
        var blueBallProbability = ballServices["blue"]?.getBallProbability()!!
        var blueBall = getBall(blueList, blueBallProbability)

        return "红球==>：${JSON.toJSONString(redBallList)}   篮球==>：${blueBall}"
    }

    private fun getBall(ballList: MutableList<Int>, ballProbabilitys: List<BallCountVO>): Int {
        var lastScope = 0
        val ballScopes = Maps.newHashMap<Int, IntArray>()
        // 洗牌，打乱号码次序
        ballList.shuffle()
        for (ball in ballList) {
            var probability = 0
            for (ballProbability in ballProbabilitys) {
                if (ball ==  ballProbability.ball!!.toInt()) {
                    probability = ballProbability.count!!
                    break
                }
            }
            if (probability == 0) {
                continue
            }
            // 划分区间
            val currentScope = lastScope + (probability * MULTIPLE)
            ballScopes[ball] = intArrayOf(lastScope + 1, currentScope)
            lastScope = currentScope
        }
        return drawLottery(ballScopes, lastScope)
    }

    private fun drawLottery(scopes: HashMap<Int, IntArray>, maxScope: Int): Int {
        // 获取1-1000000之间的一个随机数
        val luckyNumber = Random().nextInt(maxScope)
        // 判断命中片区
        val prizeEntrySets = scopes.entries
        for (prizeEntry in prizeEntrySets) {
            val key = prizeEntry.key
            val value = prizeEntry.value
            // 命中片区
            if (luckyNumber >= value[0] && luckyNumber <= value[1]) {
                return key
            }
        }
        return 0
    }

}