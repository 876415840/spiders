package com.example.demo.entity

import org.apache.commons.lang3.builder.ReflectionToStringBuilder
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

    companion object {
        /**
         * 表名
         */
        const val TABLE_NAME = "shuang_se_qiu"
    }
}
