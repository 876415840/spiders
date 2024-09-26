package com.example.demo.tax

import com.example.demo.enumerate.IndividualIncomeTaxLevelEnum
import java.lang.StringBuilder
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 个人税计算参数
 *
 * @author stephen
 * @date 2021/2/8 10:39 上午
 */
class IndividualTaxCompute(private var amount: BigDecimal, private var month: Int): TaxCompute() {

    /**
     * 社保公积金缴纳基数上限
     */
    private var upLimit: BigDecimal = BigDecimal(29732)

    /**
     * 公积金系数(百分点)
     */
    private var housingFundPoint: String = "12"

    /**
     * 社保公积金缴纳基数
     */
    private var base: BigDecimal = amount

    /**
     * 专项扣减额
     */
    private var other: BigDecimal = BigDecimal(1500)

    /**
     * 小贴士
     */
    private var tips: String = ""

    constructor(amount: BigDecimal, month: Int, housingFundPoint: String?, other: BigDecimal?, base: BigDecimal?) : this(amount, month) {
        var sb = StringBuilder()
        if (housingFundPoint != null) {
            this.housingFundPoint = housingFundPoint
        } else {
            sb.append("设置公积金百分比(默认12)：housingFundPoint=12 <br>")
        }
        if (other != null) {
            this.other = other
        } else {
            sb.append("设置专项扣除金额(默认1500)：other=1500 <br>")
        }
        if (base != null) {
            this.base = base
        } else {
            sb.append("设置社保公积金缴纳基数(默认等于月薪)：base=").append(amount).append(" <br>")
        }
        this.tips = sb.toString()
    }
    /**
     * 起征额(元)
     */
    private var startAmount = BigDecimal(5000)

    override fun compute(): Map<String, String>? {
        var result = HashMap<String, String>()
        // 基数上限
        this.base = if (base > upLimit) upLimit else base
        // 养老 8%,失业 0.2%,医疗 2% + 3元,住房公积金 12%(默认)
        var ssfPoint = BigDecimal(10.2).add(BigDecimal(housingFundPoint))
        var ssfRatio = ssfPoint.divide(BigDecimal(100))
        var ssf = base.multiply(ssfRatio)
        ssf = ssf.add(BigDecimal(3))
        // 月应纳税所得额 = 月工资 - 社保公积金 - 其他专项扣减 - 起征额
        var monthAmount = amount.subtract(ssf).subtract(other).subtract(startAmount)
        if (monthAmount <= BigDecimal.ZERO) {
            return null
        }

        // 当前应纳税所得额(年)
        var oldAmount = monthAmount.multiply(BigDecimal(month))
        var taxLevelEnum = IndividualIncomeTaxLevelEnum.priceOf(oldAmount)
        var tax = monthAmount.multiply(BigDecimal(taxLevelEnum!!.tax)).divide(BigDecimal(100))
        tax = tax.setScale(2, RoundingMode.HALF_UP)
        result["incomeReal"] = amount.subtract(ssf).subtract(tax).setScale(2, RoundingMode.HALF_UP).toString()
        result["ssf"] = ssf.setScale(2, RoundingMode.HALF_UP).toString()
        result["other"] = other.toString()
        result["start"] = startAmount.toString()
        result["tax"] = tax.toString()
        result["tips"] = tips
        result["base"] = base.toString()
        return result
    }


}