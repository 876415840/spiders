package com.example.demo.controller

import com.example.demo.service.LotteryService
import com.example.demo.service.RealtyDataService
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture

/**
 * @Description 彩票
 * @Author mengqinghao
 * @Date 2019/1/17 10:11 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("/lottery")
class LotteryController {

    @Autowired
    lateinit var lotteryService: LotteryService

    @Autowired
    @Qualifier("shuangSeQiuLotteryDataService")
    lateinit var realtyDataService: RealtyDataService

    /**
     * @Description: 获取双色球
     * @author MengQingHao
     * @date 2019/11/18 12:02 下午
     * @version 1.0
     */
    @GetMapping("/get")
    fun getNumbers(): String {
        var number1 = lotteryService.getBalls()
        var number2 = lotteryService.getBalls()
        var number3 = lotteryService.getBalls()
        var number4 = lotteryService.getBalls()
        var number5 = lotteryService.getBalls()
        return StringUtils.join(number1, "\n", number2, "\n", number3, "\n", number4, "\n", number5)
    }

    /**
     * 爬历史双色球
     */
    @GetMapping("/spiders/shuangSeQiu")
    fun spidersOldNumbers(): String {
        var index = realtyDataService.getIndex()
        CompletableFuture.runAsync { realtyDataService.spiderData(); }
        return "调用${index}次"
    }

}
