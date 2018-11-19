package com.example.demo.controller

import com.example.demo.service.RealtyDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/11/14 7:44 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/spiders")
class SpidersController {

    @Autowired lateinit var realtyDataService: RealtyDataService

    @GetMapping("/realtyInfo")
    fun realtyInfo(): String {
        realtyDataService.spiderData()
        return "hello world!"
    }
}
