package com.example.demo.service.impl

import com.example.demo.mapper.ShuangSeQiuMapper
import com.example.demo.service.BallService
import com.example.demo.vo.BallCountVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 蓝球
 * @Author MengQingHao
 * @Date 2019/11/15 11:45 上午
 * @Version 1.0
 */
@Service("blue")
class BallBlueServiceImpl: BallService {

    @Autowired lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    override fun getBallProbability(): List<BallCountVO> {
        var ballCounts = shuangSeQiuMapper.getBlueBallCount()
        setBallProbability(ballCounts)
        return ballCounts
    }
}