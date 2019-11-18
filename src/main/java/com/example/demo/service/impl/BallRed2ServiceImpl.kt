package com.example.demo.service.impl

import com.example.demo.mapper.ShuangSeQiuMapper
import com.example.demo.service.BallService
import com.example.demo.vo.BallCountVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 红2球
 * @Author MengQingHao
 * @Date 2019/11/15 2:43 下午
 * @Version 1.0
 */
@Service("red2")
class BallRed2ServiceImpl: BallService {

    @Autowired
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    override fun getBallProbability(): List<BallCountVO> {
        var ballCounts = shuangSeQiuMapper.getRed2BallCount()
        setBallProbability(ballCounts)
        return ballCounts
    }

}