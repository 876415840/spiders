package com.example.demo.util;

import cn.hutool.extra.mail.GlobalMailAccount;
import cn.hutool.extra.mail.MailAccount;
import com.example.demo.mail.MailSMTP;

import java.io.IOException;

/**
 * 邮件工具类
 *
 * @author stephen
 * @date 2020/12/29 3:11 下午
 */
public class MailUtil {

    /**
     * @param to 接收邮箱账户，多个用;隔开
     * @param subject 标题
     * @param content 内容
     */
    public static void send(String to, String subject, String content) {
        MailAccount mailAccount = GlobalMailAccount.INSTANCE.getAccount();
        MailSMTP mailSMTP = new MailSMTP(mailAccount.getHost(), mailAccount.getUser(), mailAccount.getPass());
        mailSMTP.send(to, subject,content);
        try {
            mailSMTP.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
