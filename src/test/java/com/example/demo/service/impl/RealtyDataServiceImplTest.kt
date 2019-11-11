package com.example.demo.service.impl

import com.example.demo.config.mq.rabbit.TestMqConfig
import com.example.demo.entity.HouseInfo
import com.example.demo.service.RabbitmqService
import com.example.demo.service.RealtyDataService
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.support.CorrelationData
import org.springframework.beans.factory.annotation.Autowired

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
    lateinit var rabbitmqService: RabbitmqService

    @org.junit.Test
    fun spiderData() {
        for (service in realtyDataServices.values) {
            service.spiderData()
        }
        LOGGER.info("================================>{}", "test")
    }

    @org.junit.Test
    fun mq() {
        var houseInfo = HouseInfo()
        houseInfo.title = "测试标题"
        var correlationData = CorrelationData()
        correlationData.id = "test_corre_id_1"
        // exchange,queue 都正确,confirm被回调, ack=true
        rabbitmqService.convertAndSend(TestMqConfig.EXCHANGE_TEST, TestMqConfig.QUEUE_TEST, houseInfo, correlationData)
        LOGGER.info("=======1=========================>{}", "test")
        Thread.sleep(1000)
        // exchange 错误,queue 正确,confirm被回调, ack=false
        rabbitmqService.convertAndSend(TestMqConfig.EXCHANGE_TEST+"111", TestMqConfig.QUEUE_TEST, houseInfo, correlationData)
        LOGGER.info("=======2=========================>{}", "test")
        Thread.sleep(1000)
        // exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
        rabbitmqService.convertAndSend(TestMqConfig.EXCHANGE_TEST, TestMqConfig.QUEUE_TEST+"111", houseInfo, correlationData)
        LOGGER.info("=======3=========================>{}", "test")
        Thread.sleep(1000)
        // exchange 错误,queue 错误,confirm被回调, ack=false
        rabbitmqService.convertAndSend(TestMqConfig.EXCHANGE_TEST+"111", TestMqConfig.QUEUE_TEST+"111", houseInfo, correlationData)
        LOGGER.info("=======4=========================>{}", "test")
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(RealtyDataServiceImplTest::class.java)
    }


}
