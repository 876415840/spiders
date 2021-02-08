package com.example.demo.tax

import java.math.BigDecimal

/**
 * 税计算参数基类
 *
 * @author stephen
 * @date 2021/2/8 10:38 上午
 */
open abstract class TaxCompute() {

    /**
     * 计算税费
     */
    open abstract fun compute() : Map<String, String>?
}