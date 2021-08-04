package com.example.demo.config.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Description test mq
 * @Author MengQingHao
 * @Date 2019/8/8 2:11 PM
 * @Version 1.0
 */
//@Configuration
public class TestMqConfig {

    public final static String EXCHANGE_TEST = "exchange.test";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_TEST);
    }

    public final static String QUEUE_TEST = "queue.test";
    @Bean
    Queue payTouchLotteryQueue() {
        return new Queue(QUEUE_TEST, true);
    }
    @Bean
    Binding payTouchLotteryBinding() {
        return BindingBuilder.bind(payTouchLotteryQueue()).to(exchange()).withQueueName();
    }
}
