package com.example.demo.entity

import java.io.Serializable
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * JobDetail实体
 * @author 系统生成
 */
class JobDetail : Serializable {


    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 技术名词
     */
    var techTerm: String? = null

    /**
     * 出现次数
     */
    var count: Int? = null

    /**
     * 所属标签
     */
    var tag: String? = null

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
        const val TABLE_NAME = "job_detail"
    }
}