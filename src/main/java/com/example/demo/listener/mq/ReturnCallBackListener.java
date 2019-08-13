package com.example.demo.listener.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/8/9 6:48 PM
 * @Version 1.0
 */
@Slf4j
@Service
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("return==========->{}->{}->{}->{}->{}", JSON.toJSONString(message), i, s, s1, s2);

    }
}
