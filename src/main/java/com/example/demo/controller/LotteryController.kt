package com.example.demo.controller

import com.alibaba.fastjson.JSON
import com.google.common.collect.Lists
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @Description 彩票
 * @Author mengqinghao
 * @Date 2019/1/17 10:11 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("/lottery")
class LotteryController {

    @GetMapping("/get")
    fun getNumbers(): String {
        var redList = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33)
        var blueList: List<Int> = mutableListOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
        var redResultList = Lists.newArrayList<Int>()
        for (i in 1..6){
            var index = Random().nextInt(redList.size)
            var num: Int = redList[index]
            redResultList.add(num)
            redList.removeAt(index)
        }
        redResultList.sort()
        var blueResult = Random().nextInt(blueList.size)+1
        return "红球：${JSON.toJSONString(redResultList)}   篮球：${blueResult}"
    }

}
