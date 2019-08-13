package com.example.demo.listener.mq;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.mq.rabbit.TestMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/8/8 5:52 PM
 * @Version 1.0
 */
@Component
@Slf4j
public class TestListener {

    @RabbitHandler
    @RabbitListener(queues = TestMqConfig.QUEUE_TEST)
    public void testExecute(Object obj) throws Exception {
        log.info("=========================>{}", JSON.toJSONString(obj));
    }
}
