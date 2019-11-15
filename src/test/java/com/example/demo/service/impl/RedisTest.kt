package com.example.demo.service.impl

import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.test.context.junit4.SpringRunner

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/7/29 7:30 PM
 * @Version 1.0
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class RedisTest {

    companion object {

        private val LOGGER = LoggerFactory.getLogger(RealtyDataServiceImplTest::class.java)
    }

    @Autowired lateinit var redisTemplate: RedisTemplate<String, String>

    @org.junit.Test
    fun test() {
        redisTemplate.opsForValue().set("key1","value1")
        val value: String = redisTemplate.opsForValue().get("key1")
        LOGGER.info("=========> {}", value)
    }
}