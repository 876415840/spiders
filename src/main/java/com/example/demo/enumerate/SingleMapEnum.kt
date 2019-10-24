package com.example.demo.enumerate

import com.example.demo.entity.HouseInfo

/**
 * @Description 单例map容器
 * @Author MengQingHao
 * @Date 2019/10/24 11:29 AM
 * @Version 1.0
 */
enum class SingleMapEnum private constructor() {
    SINGLE_DEMO;

    val map: MutableMap<String, HouseInfo> = mutableMapOf()

}
