package com.example.demo.service.impl

import cn.hutool.extra.mail.MailUtil
import com.example.demo.mapper.TransactionInfoMapper
import com.example.demo.service.WarnService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

/**
 * @Description: 交易信息任务通知
 * @Author MengQingHao
 * @Date 2020/7/2 6:18 下午
 */
@Service
class TransactionJobWarnServiceImpl: WarnService {

    @Value("\${spring.mail.to-mail}")
    private val toMail: String? = null

    @Autowired
    private
    lateinit var transactionInfoMapper: TransactionInfoMapper

    override fun jobWarn() {
        val transactionCount = transactionInfoMapper.todayTransactionCount()
        if (transactionCount > 0) {
            MailUtil.send(toMail, "新增成交数$transactionCount", "nothing", false)
        }
    }
}