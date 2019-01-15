package com.example.demo.service.impl

import com.example.demo.enum.IndividualIncomeTaxLevelEnum
import com.example.demo.service.TaxComputeService
import org.springframework.stereotype.Service
import java.math.BigDecimal

/**
 * @Description 税额计算接口实现
 * @Author mengqinghao
 * @Date 2019/1/12 6:19 PM
 * @Version 1.0
 */
@Service
class TaxComputeServiceImpl: TaxComputeService {

    /**
     * 个人所得税计算
     *
     * @author mengqinghao
     * @param month 月份
     * @param individualIncome 个人收入
     * @param isCountry 是否农村户口
     * @date 2019/1/12 6:15 PM
     * @return
     */
    override fun individualIncomeTax(month: Int, individualIncome: Int, isCountry: Boolean): BigDecimal {
        // 个人工资 计算基数
        var price  = BigDecimal(individualIncome)
        // 住房公积金(按最高比计算)
        var housingFund = price.multiply(BigDecimal(0.12))
        // 养老保险
        var endowmentInsurance = price.multiply(BigDecimal(0.08))
        // 医疗保险
        var medicalInsurance = price.multiply(BigDecimal(0.02)).add(BigDecimal(3))
        // 失业保险(非农村户口-城镇户口才需要个人缴纳失业保险)
        var unemploymentInsurance = BigDecimal.ZERO
        if(!isCountry){
            unemploymentInsurance = price.multiply(BigDecimal(0.002))
        }
        price = price.subtract(housingFund).subtract(endowmentInsurance).subtract(medicalInsurance).subtract(unemploymentInsurance)
                .subtract(BigDecimal(5000))
        // 已交税金
        price = price.multiply(BigDecimal(month))
        var taxLevelEnum = IndividualIncomeTaxLevelEnum.priceOf(price)
        var tax = price.multiply(BigDecimal(taxLevelEnum!!.tax)).divide(BigDecimal(100))
        tax = tax.setScale(2,BigDecimal.ROUND_HALF_UP)
        return tax
    }
}