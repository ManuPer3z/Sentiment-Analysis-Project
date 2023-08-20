package com.misentiment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SentimentController {

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello from the Sentiment Controller!";
    }
}
