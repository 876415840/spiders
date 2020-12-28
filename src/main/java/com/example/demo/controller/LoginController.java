package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author MengQingHao
 * @Date 2020/9/3 5:09 下午
 */
@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        log.info("------------------ {} ----------------", "longin");
        modelMap.addAttribute("key", "test");
        // TODO:MQH 2020/12/28 not right
        return "index";
    }
}
