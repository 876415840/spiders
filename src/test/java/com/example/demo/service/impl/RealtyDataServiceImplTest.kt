package com.example.demo.service.impl

import com.example.demo.service.LotteryService
import com.example.demo.service.RealtyDataService
import com.example.demo.service.WarnService
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/5/16 7:28 PM
 * @Version 1.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RealtyDataServiceImplTest {

    @Autowired
    lateinit var realtyDataServices: Map<String, RealtyDataService>

    @Autowired
    lateinit var warnServices: Map<String, WarnService>

    @Autowired
    lateinit var lotteryService: LotteryService

    @org.junit.Test
    fun getBalls() {
        var balls = lotteryService.getBalls()
        LOGGER.info("===============双色球=================>{}", balls)
    }

    @org.junit.Test
    fun spiderData() {
        for (service in realtyDataServices.values) {
            service.spiderData()
        }

        LOGGER.info("================================> {}", "spiderData -- job")
        for (service in warnServices.values) {
            service.jobWarn()
        }
        LOGGER.info("================================> {}", "jobWarn")
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(RealtyDataServiceImplTest::class.java)
    }


}
