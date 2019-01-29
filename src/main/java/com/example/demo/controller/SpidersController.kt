package com.example.demo.controller

import com.alibaba.fastjson.JSON
import com.example.demo.mapper.HouseInfoMapper
import com.example.demo.service.RealtyDataService
import org.springframework.beans.factory.annotation.Autowired
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

    @Autowired
    lateinit var houseInfoMapper: HouseInfoMapper

    @GetMapping("/realtyInfo/{code}")
    fun realtyInfo(@PathVariable("code") code : String): String {
        var houseInfo = houseInfoMapper.getByCode(code)
        return JSON.toJSONString(houseInfo)
    }
}
