package com.example.demo.service;


import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/8/8 6:10 PM
 * @Version 1.0
 */
public interface RabbitmqService {

    /**
     * 发送消息
     *
     * @author MengQingHao
     * @param exchange
     * @param routingKey
     * @param object
     * @param correlationData
     * @date 2019/8/8 6:16 PM
     * @return boolean
     */
    boolean convertAndSend(String exchange, String routingKey, final Object object, CorrelationData correlationData);
}
