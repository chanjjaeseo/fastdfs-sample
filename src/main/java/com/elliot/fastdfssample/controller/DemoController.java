package com.elliot.fastdfssample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class DemoController {

    @RequestMapping("/demo")
    public String demo() {
        return "/resources/index.html";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

}
