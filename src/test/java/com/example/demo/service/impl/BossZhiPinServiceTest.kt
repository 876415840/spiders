package com.example.demo.service.impl

import com.example.demo.service.JobService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author stephen
 * @date 2020/10/28 4:17 下午
 */
@RunWith(SpringRunner::class)
@SpringBootTest
internal class BossZhiPinServiceTest {

    @Autowired
    lateinit var bossZhiPinService: JobService

    @Test
    fun spiderData() {
        bossZhiPinService.spiderData()
    }
}