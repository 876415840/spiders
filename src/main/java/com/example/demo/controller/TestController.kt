package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

/**
 *
 *
 * @author stephen
 * @date 2020/10/30 12:11 下午
 */
@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping("/test")
    fun spidersOldNumbers(): String {
        return InetAddress.getLocalHost().hostAddress
    }
}