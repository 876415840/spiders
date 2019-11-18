package com.example.demo.service.impl

import com.example.demo.mapper.ShuangSeQiuMapper
import com.example.demo.service.BallService
import com.example.demo.vo.BallCountVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 红1球
 * @Author MengQingHao
 * @Date 2019/11/15 2:43 下午
 * @Version 1.0
 */
@Service("red1")
class BallRed1ServiceImpl: BallService {

    @Autowired
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    override fun getBallProbability(): List<BallCountVO> {
        var ballCounts = shuangSeQiuMapper.getRed1BallCount()
        setBallProbability(ballCounts)
        return ballCounts
    }

}