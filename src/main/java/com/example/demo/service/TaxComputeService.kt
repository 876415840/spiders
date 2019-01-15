package com.example.demo.service

import java.math.BigDecimal

/**
 * @Description 税额计算接口
 * @Author mengqinghao
 * @Date 2019/1/12 6:06 PM
 * @Version 1.0
 */
interface TaxComputeService {

    /**
     * 个人所得税计算
     *
     * @author mengqinghao 
     * @param month 月份
     * @param individualIncome 个人收入
     * @param isCountry 是否农村户口
     * @date 2019/1/12 6:15 PM
     * @return
     */
    fun individualIncomeTax(month: Int,individualIncome: Int, isCountry: Boolean):BigDecimal
}