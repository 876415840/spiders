package com.example.demo.controller

import cn.hutool.http.HttpUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

/**
 * @author qinghao.meng created on 2024/8/27
 * @version $Id$
 */
@RestController
@RequestMapping("/chat")
class ChatController {

    private val log: Logger = LoggerFactory.getLogger(ChatController::class.java)

    private val ollamaUrl: String = "http://localhost:11434/api/chat";
    private val ollamaBody: String = "{\"model\":\"llama3.1\",\"messages\":[{\"role\":\"user\",\"content\":\"%s\"}],\"stream\":false}";

    @GetMapping("/{chat}")
    fun chat(@PathVariable("chat") chat: String): String {
        try {
            val decodedParam = URLDecoder.decode(chat, StandardCharsets.UTF_8.name())
            val result = HttpUtil.post(ollamaUrl, String.format(ollamaBody, decodedParam))
            log.info("Chat result: {}", result)
            return result;
        } catch (e: Exception) {
            log.error("Error decoding chat parameter", e)
            return "Error:" + e.message;
        }
    }

}