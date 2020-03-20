package com.example.demo.service.impl

import org.junit.Test
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

    @Test
    fun test() {
        redisTemplate.opsForValue().set("key1","value1")
        val value: String = redisTemplate.opsForValue().get("key1")
        LOGGER.info("=========> {}", value)
    }


    /**
     * 模拟用户的相同关注  或是 相同好友
     */
    @Test
    fun attention() {
        // a的好友
        var keyA = "attention:user:a"
        var friendForA = arrayOf("1", "2", "3", "4", "5", "6")
        redisTemplate.opsForSet().add(keyA, *friendForA)
        // b的好友
        var keyB = "attention:user:b"
        var friendForB = arrayOf("3", "4", "5", "6", "7", "8")
        redisTemplate.opsForSet().add(keyB, *friendForB)
        // c的好友
        var keyC = "attention:user:c"
        var friendForC = arrayOf("5", "6", "7", "8", "9", "10")
        redisTemplate.opsForSet().add(keyC, *friendForC)

        var ab = redisTemplate.opsForSet().intersect(keyA, keyB)
        LOGGER.info("======ab交集===> {}", ab)
        var ac = redisTemplate.opsForSet().intersect(keyA, keyC)
        LOGGER.info("======ac交集===> {}", ac)
        var bc = redisTemplate.opsForSet().intersect(keyB, keyC)
        LOGGER.info("======bc交集===> {}", bc)


    }
}