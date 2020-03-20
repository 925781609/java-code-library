package com.liuil.springboot.controller;

import com.liuil.springboot.request.Color;
import com.liuil.springboot.request.DemoRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController {
    @GetMapping("/get")
    public String test(@RequestParam(required = false) String parameter1,
                       @RequestParam(required = false) Color color) {

        return parameter1 + color.toString();
    }

    @PostMapping("/post")
    public String test(@RequestBody DemoRequest demoRequest) {
        return demoRequest.toString();
    }
}
