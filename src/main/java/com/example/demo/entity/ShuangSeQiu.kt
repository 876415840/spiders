package com.example.demo.entity

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * ShuangSeQiu实体
 * @author 系统生成
 */
class ShuangSeQiu : Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 期数,始于2003001
     */
    var period: Int? = null

    /**
     * 红1
     */
    var red1: String? = null

    /**
     * 红2
     */
    var red2: String? = null

    /**
     * 红3
     */
    var red3: String? = null

    /**
     * 红4
     */
    var red4: String? = null

    /**
     * 红5
     */
    var red5: String? = null

    /**
     * 红6
     */
    var red6: String? = null

    /**
     * 蓝球
     */
    var blue: String? = null

    /**
     * 创建时间
     */
    var createTime: Date? = null

    /**
     * 更新时间
     */
    var updateTime: Date? = null

    constructor(period: Int?, red1: String?, red2: String?, red3: String?, red4: String?, red5: String?, red6: String?, blue: String?) {
        this.period = period
        this.red1 = red1
        this.red2 = red2
        this.red3 = red3
        this.red4 = red4
        this.red5 = red5
        this.red6 = red6
        this.blue = blue
    }


    companion object {
        /**
         * 表名
         */
        const val TABLE_NAME = "shuang_se_qiu"
    }
}
