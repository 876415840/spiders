package com.example.demo.entity

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * HouseInfo实体
 * @author 系统生成
 */
class PriceChange : Serializable {


    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 房屋编码
     */
    var houseCode: String? = null

    /**
     * 总价（元）
     */
    var totalPrice: Int? = null

    /**
     * 单价（元）
     */
    var unitPrice: Int? = null

    /**
     * 创建时间
     */
    var createTime: Date? = null

    /**
     * 更新时间
     */
    var updateTime: Date? = null

    companion object {
        /**
         * 表名
         */
        const val TABLE_NAME = "price_change"
    }
}