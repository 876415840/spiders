package com.example.demo.service

/**
 * @Description 房地产数据接口
 * @Author mengqinghao
 * @Date 2018/11/19 3:38 PM
 * @Version 1.0
 */
interface RealtyDataService {

    fun getIndex(): Int

    /**
     * @Description: 爬取数据
     * @author mengqinghao
     * @param:
     * @date 2018/11/19 3:57 PM
     * @return:
     */
    fun spiderData()
}