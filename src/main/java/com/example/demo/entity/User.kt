package com.example.demo.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.io.Serializable
import javax.persistence.Table

/**
 * User实体
 * @author 系统生成
 */
@Table
class User : Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 名称
     */
    var name: String? = null
}
