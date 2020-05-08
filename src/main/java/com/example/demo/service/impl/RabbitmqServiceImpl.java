package com.example.demo.service.impl;

import com.example.demo.service.RabbitmqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/8/8 6:17 PM
 * @Version 1.0
 */
@Slf4j
@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                // confirm返回成功
                log.info("messageId send success--correlationData:[{}]", correlationData == null ? "null" : correlationData.getId());
            } else {
                // 失败则进行具体的后续操作:重试 或者补偿等手段
                log.error("messageId send fail--correlationData:[{}]", correlationData == null ? "null" : correlationData.getId());
            }
        });
        this.rabbitTemplate.setReturnCallback((var1, var2, var3, var4, var5) -> {
            log.info("->{}->{}->{}->{}->{}", var1, var2, var3, var4, var5);
        });
    }


    @Override
    public boolean convertAndSend(String exchange, String routingKey, Object object, CorrelationData correlationData) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingKey, object, correlationData);
        } catch (AmqpException e) {
            log.error("send mq message error", e);
            return false;
        }
        return true;
    }
}
