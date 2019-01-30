package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/realtyInfo")
    fun realtyInfo(): String {

        return "nothing"
    }
}
