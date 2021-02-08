package com.example.demo.enumerate

import java.math.BigDecimal

/**
 * @Description 个税级别
 * @Author mengqinghao
 * @Date 2019/1/12 6:28 PM
 * @Version 1.0
 */
enum class IndividualIncomeTaxLevelEnum(val level:Int, val beginPrice:Int, val endPrice:Int, val tax:Int, val deduct:Int) {

    ONE(1,-1,36000,3,0),
    TWO(2,36000,144000,10,2520),
    THREE(3,144000,300000,20,16920),
    FOUR(4,300000,420000,25,31920),
    FIVE(5,420000,660000,30,52920),
    SIX(6,660000,960000,35,85920),
    SEVEN(7,960000, Int.MAX_VALUE,45,181920);

    companion object {

        fun priceOf(price: BigDecimal): IndividualIncomeTaxLevelEnum? {
            var enums: Array<IndividualIncomeTaxLevelEnum> = values()
            for (enum in enums){
                var begin = BigDecimal(enum.beginPrice)
                var end = BigDecimal(enum.endPrice)
                if(begin < price && price <= end){
                    return enum
                }
            }
            return null
        }
    }
}