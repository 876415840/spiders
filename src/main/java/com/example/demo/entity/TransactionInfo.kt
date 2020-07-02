package com.example.demo.entity

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * TransactionInfo实体
 * @author 系统生成
 */
class TransactionInfo: Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 房屋编码
     */
    var code: String? = null

    /**
     * 交易日期
     */
    var transactionDate: String? = null

    /**
     * 成交周期（天）
     */
    var transactionPeriod: Int? = null

    /**
     * 成交价（万元）
     */
    var dealPrice: String? = null

    /**
     * 挂牌价（元）
     */
    var stickerPrice: Int? = null

    /**
     * 调价次数
     */
    var adjustCount: Int? = null

    /**
     * 带看次数
     */
    var lookCount: Int? = null

    /**
     * 关注人数
     */
    var follow: Int? = null

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
        const val TABLE_NAME = "transaction_info"
    }

}