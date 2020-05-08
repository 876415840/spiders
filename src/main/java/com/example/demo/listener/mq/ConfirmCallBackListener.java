package com.example.demo.listener.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/8/9 6:45 PM
 * @Version 1.0
 */
@Slf4j
@Service
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        if (ack) {
            // confirm返回成功
            log.info("messageId send success--correlationData:[{}]", correlationData == null ? "null" : correlationData.getId());
        } else {
            // 失败则进行具体的后续操作:重试 或者补偿等手段
            log.error("messageId send fail--correlationData:[{}]", correlationData == null ? "null" : correlationData.getId());
        }
    }
}
