package com.example.demo.service.impl

import com.example.demo.mapper.ShuangSeQiuMapper
import com.example.demo.service.BallService
import com.example.demo.vo.BallCountVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Description: 红3球
 * @Author MengQingHao
 * @Date 2019/11/15 2:43 下午
 * @Version 1.0
 */
@Service("red3")
class BallRed3ServiceImpl: BallService {

    @Autowired
    lateinit var shuangSeQiuMapper: ShuangSeQiuMapper

    override fun getBallProbability(): List<BallCountVO> {
        var ballCounts = shuangSeQiuMapper.getRed3BallCount()
        setBallProbability(ballCounts)
        return ballCounts
    }

}