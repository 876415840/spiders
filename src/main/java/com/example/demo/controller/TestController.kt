package com.example.demo.controller

import com.example.demo.service.JobService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 *
 * @author stephen
 * @date 2020/10/30 12:11 下午
 */
@RestController
@RequestMapping("/test")
class TestController {

    @Autowired
    lateinit var bossZhiPinService: JobService

    @GetMapping("/test")
    fun spidersOldNumbers(): String {
        bossZhiPinService.spiderData()
        return "处理完"
    }
}