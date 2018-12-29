package com.example.demo.entity

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import java.io.Serializable
import java.util.Objects
import org.apache.commons.lang3.builder.ReflectionToStringBuilder

/**
 * HouseInfo实体
 * @author 系统生成
 */
class HouseInfo : Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    /**
     * 标题
     */
    var title: String? = null

    /**
     * 房屋编码
     */
    var code: String? = null

    /**
     * 小区
     */
    var housingEstate: String? = null

    /**
     * 规格
     */
    var specification: String? = null

    /**
     * 房屋大小(平方分米)
     */
    var size: Int? = null

    /**
     * 朝向
     */
    var orientation: String? = null

    /**
     * 装修类型
     */
    var decorationType: String? = null

    /**
     * 是否有电梯
     */
    var elevator: Int? = null

    /**
     * 楼层类型
     */
    var storeyType: String? = null

    /**
     * 年份
     */
    var year: Int? = null

    /**
     * 楼类型
     */
    var towerType: String? = null

    /**
     * 地区
     */
    var area: String? = null

    /**
     * 总价（元）
     */
    var totalPrice: Int? = null

    /**
     * 类型
     */
    var type: String? = null

    /**
     * 单价（元）
     */
    var unitPrice: Int? = null

    /**
     * 是否近地铁
     */
    var subway: Int? = null

    /**
     * 是否VR防御
     */
    var vr: Int? = null

    /**
     * 是否房本满五年
     */
    var taxfree: Int? = null

    /**
     * 是否随时看房
     */
    var anyTime: Int? = null

    companion object {
        /**
         * 表名
         */
        const val TABLE_NAME = "house_info"
    }
}
