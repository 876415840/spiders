package com.example.demo.controller

import com.example.demo.tax.IndividualTaxCompute
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

/**
 * @Description 税率计算
 * @Author mengqinghao
 * @Date 2019/1/15 1:50 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/taxCompute")
class TaxComputeController {

    private val log: Logger = LoggerFactory.getLogger(TaxComputeController::class.java)

    /**
     * 个人所得税计算
     *
     * @author mengqinghao 
     * @param amount 月金额
     * @date 2019/1/15 1:59 PM
     * @return
     */
    @GetMapping("/personal/{amount}")
    fun personal(@PathVariable("amount") amount: BigDecimal,
                 @RequestParam(value = "other", required = false) other: BigDecimal?,
                 @RequestParam(value = "base", required = false) base: BigDecimal?,
                 @RequestParam(value = "housingFundPoint", required = false) housingFundPoint: String?): String {
        if (amount <= BigDecimal.ZERO) {
            return "0";
        }
        var sb = StringBuilder()
        var sumTax = BigDecimal.ZERO
        var sumIncomeReal = BigDecimal.ZERO
        for (i in 1..12) {
            var taxCompute = IndividualTaxCompute(amount, month = i, other = other, housingFundPoint = housingFundPoint, base = base)
            var taxInfo: Map<String, String>? = taxCompute.compute() ?: return "--"
            if (1 == i) {
                var tips = taxInfo?.get("tips")
                if (tips!!.isNotEmpty()) {
                    sb.append(tips).append("<br><br><br>")
                }
                sb.append("月薪 ：").append(amount).append("；扣缴基数：").append(taxInfo?.get("base")).append("；专项扣除：")
                        .append(taxInfo?.get("other")).append("；起征额：").append(taxInfo?.get("start"))
                        .append("；社保公积金：").append(taxInfo?.get("ssf")).append("；<br>")
            }
            var tax = taxInfo?.get("tax")
            var incomeReal = taxInfo?.get("incomeReal")
            sumTax = sumTax.add(BigDecimal(tax))
            sumIncomeReal = sumIncomeReal.add(BigDecimal(incomeReal))
            sb.append(i).append("月税费：").append(tax).append("；实收：").append(incomeReal).append("；<br>")
        }
        sb.append("总税费：").append(sumTax).append("；总实收：").append(sumIncomeReal).append("；")

        log.info("--------------> {}", sb.toString())
        return sb.toString()
    }

}