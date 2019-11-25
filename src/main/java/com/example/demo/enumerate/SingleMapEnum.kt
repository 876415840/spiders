package com.example.demo.enumerate

import com.example.demo.entity.HouseInfo
import com.example.demo.vo.PriceChangeVO

/**
 * @Description 单例map容器
 * @Author MengQingHao
 * @Date 2019/10/24 11:29 AM
 * @Version 1.0
 */
enum class SingleMapEnum {
    SINGLE_DEMO;

    val houseInfoByCode: MutableMap<String, HouseInfo> = mutableMapOf()
    val exceptions: MutableList<String> = ArrayList()
    val priceChanges: MutableList<PriceChangeVO> = ArrayList()
    /**
     * 双色球爬取失败记录
     */
    val lotteryFailed: MutableList<Int> = ArrayList()

}
