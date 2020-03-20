package com.liuil.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.liuil.springboot.request.DemoRequest;

@RestController
public class DemoController {
    @GetMapping("/get")
    public String test(@RequestParam(required = false) String parameter1,
                       @RequestParam(required = false) String parameter2) {

        return parameter1 + parameter2;
    }

    @PostMapping("/post")
    public String test(DemoRequest demoRequest) {
        return demoRequest.toString();

    }
}
