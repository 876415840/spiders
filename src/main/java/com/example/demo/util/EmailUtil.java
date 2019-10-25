package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description
 * @Author MengQingHao
 * @Date 2019/10/25 11:06 AM
 * @Version 1.0
 */
@Component
public class EmailUtil {
    @Autowired
//    private JavaMailSender sender;

//    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送一般文本邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendTextEmail(String to,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setSentDate(new Date());
        //todo
//        sender.send(message);
    }

}
