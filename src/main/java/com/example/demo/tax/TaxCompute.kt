package com.example.demo.tax

/**
 * 税计算参数基类
 *
 * @author stephen
 * @date 2021/2/8 10:38 上午
 */
abstract class TaxCompute() {

    /**
     * 计算税费
     */
    abstract fun compute() : Map<String, String>?
}