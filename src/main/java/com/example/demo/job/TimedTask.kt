package com.example.demo.job

import com.example.demo.service.RealtyDataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

/**
 * @Description TODO
 * @Author mengqinghao
 * @Date 2018/12/24 7:16 PM
 * @Version 1.0
 */
@Component
class TimedTask {

    @Autowired lateinit var realtyDataService: RealtyDataService

    /**
     * @Description: 凌晨定时任务
     * @author mengqinghao
     * @param: 
     * @date 2018/12/24 7:26 PM
     * @return: 
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    fun WeeHours() {
//        realtyDataService.spiderData()
        println("==================>")
    }
}