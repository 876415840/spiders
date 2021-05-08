package com.example.demo.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Base64;

/**
 * 发送邮件
 *
 * @author stephen
 * @date 2020/12/29 11:04 上午
 */
public class MailSMTP {

    private String host;
    private String sender;
    private String password;

    private int port = 25;
    private int timeout = 3000;
    private boolean connect = false;

    private Socket socket;
    private BufferedReader reader;
    private DataOutputStream writer;

    private static final String CONNECT_SUCCESS = "220";
    private static final String CONFIRM_SUCCESS = "250";
    private static final String AUTH_LOGIN_SUCCESS = "334";
    private static final String LOGIN_SUCCESS = "235";
    private static final String DATA_SUCCESS = "354";
    private static final String QUIT_SUCCESS = "221";

    private static final String LINE_TAIL = "\r\n";
    private static final String HELO = "HELO ";
    private static final String AUTH_LOGIN = "AUTH LOGIN";
    private static final String MAIL_FROM = "MAIL FROM: ";
    private static final String RCPT_TO = "RCPT TO: ";
    private static final String SUBJECT = "SUBJECT:";
    private static final String DATA = "DATA";
    private static final String QUIT = "QUIT";
    private static final String LT = "<";
    private static final String GT = ">";
    private static final String CHARSET_NAEM = "UTF-8";

    public MailSMTP(String host, String sender, String password) {
        Assert.isTrue(StringUtils.isNotBlank(host), "'host' is null");
        Assert.isTrue(StringUtils.isNotBlank(sender), "'sender' is null");
        Assert.isTrue(StringUtils.isNotBlank(password), "'password' is null");
        this.host = host;
        this.sender = sender;
        this.password = password;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private void connect() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new DataOutputStream(socket.getOutputStream());
            String result = reader.readLine();
            if (!result.startsWith(CONNECT_SUCCESS)) {
                throw new RuntimeException("连接到 " + host + ":" + port + " 失败, result: " + result);
            }
            connect = true;
        } catch (IOException e) {
            throw new RuntimeException("连接到 " + host + ":" + port + " 失败", e);
        }
    }


    private String sendCommand(String... command) {
        StringBuilder writers = new StringBuilder();
        for (String s : command) {
            writers.append(s);
        }
        writers.append(LINE_TAIL);
        try {
            byte[] bytes = writers.toString().getBytes(CHARSET_NAEM);
            writer.write(bytes);
            writer.flush();
            StringBuilder sb = new StringBuilder(reader.readLine());
            String line;
            while (reader.ready()) {
                sb.append(LINE_TAIL);
                sb.append(reader.readLine());
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("发送命令异常");
        }
    }

    private boolean login() {
        String result = sendCommand(HELO, host);
        if (!result.startsWith(CONFIRM_SUCCESS)) {
            return false;
        }
        result = sendCommand(AUTH_LOGIN);
        if (!result.startsWith(AUTH_LOGIN_SUCCESS)) {
            return false;
        }
        result = sendCommand(new String(Base64.getEncoder().encode(sender.getBytes())));
        if (!result.startsWith(AUTH_LOGIN_SUCCESS)) {
            return false;
        }
        result = sendCommand(new String(Base64.getEncoder().encode(password.getBytes())));
        if (!result.startsWith(LOGIN_SUCCESS)) {
            return false;
        }

        return true;
    }

    /**
     * 发送邮件
     *
     * @param receivers 接收用户,多个用;隔开
     * @param title
     * @param context
     */
    public void send(String receivers, String title, String context) {
        if (!connect) {
            connect();
            boolean login = login();
            if (!login) {
                throw new RuntimeException("邮箱登录异常");
            }
        }
        boolean send = sendMsg(receivers, title, context);
        if (!send) {
            throw new RuntimeException("邮件发送失败");
        }
        String result = sendCommand(QUIT);
        if (!result.startsWith(QUIT_SUCCESS)) {
            throw new RuntimeException("邮箱退出异常");
        }
    }

    private boolean sendMsg(String receivers, String title, String context) {
        String result = sendCommand(MAIL_FROM, LT, sender, GT);
        if (!result.startsWith(CONFIRM_SUCCESS)) {
            return false;
        }
        result = sendCommand(RCPT_TO, LT, receivers, GT);
        if (!result.startsWith(CONFIRM_SUCCESS)) {
            return false;
        }
        result = sendCommand(DATA);
        if (!result.startsWith(DATA_SUCCESS)) {
            return false;
        }
        String[] contextCommand = new String[]{SUBJECT, title, LINE_TAIL, "Content-Type:text/plain;charset=\"",
                CHARSET_NAEM, "\"", LINE_TAIL, LINE_TAIL, context, LINE_TAIL, "."};

        result = sendCommand(contextCommand);
        if (!result.startsWith(CONFIRM_SUCCESS)) {
            return false;
        }
        return true;
    }


    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
