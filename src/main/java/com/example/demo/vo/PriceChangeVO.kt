package com.example.demo.vo


/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/10/24 6:30 PM
 * @Version 1.0
 */
class PriceChangeVO {

    /**
     * 地区
     */
    var area: String? = null

    /**
     * 小区
     */
    var housingEstate: String? = null

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
     * 原来总价（元）
     */
    var oldTotalPrice: Int? = null

    /**
     * 原来单价（元）
     */
    var oldUnitPrice: Int? = null

    constructor(area: String?, housingEstate: String?, houseCode: String?, totalPrice: Int?, unitPrice: Int?, oldTotalPrice: Int?, oldUnitPrice: Int?) {
        this.area = area
        this.housingEstate = housingEstate
        this.houseCode = houseCode
        this.totalPrice = totalPrice
        this.unitPrice = unitPrice
        this.oldTotalPrice = oldTotalPrice
        this.oldUnitPrice = oldUnitPrice
    }


}