package com.example.demo.controller

import com.example.demo.service.TaxComputeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @Description 税率计算
 * @Author mengqinghao
 * @Date 2019/1/15 1:50 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/taxCompute")
class TaxComputeController {

    @Autowired
    lateinit var taxComputeService: TaxComputeService

    /**
     * 个人所得税计算
     *
     * @author mengqinghao 
     * @param month 月份
     * @param income 个人收入
     * @param isCountry 是否农村户口
     * @date 2019/1/15 1:59 PM
     * @return
     */
    @GetMapping("/individual")
    fun realtyInfo(@RequestParam("month") month: Int, @RequestParam("income") income: Int, @RequestParam("isCountry") isCountry: Boolean): String {
        var tax = taxComputeService.individualIncomeTax(month,income,isCountry)
        return tax.toString()
    }
}