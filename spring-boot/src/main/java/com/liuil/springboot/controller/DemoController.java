package com.liuil.springboot.controller;

import com.liuil.springboot.request.Color;
import com.liuil.springboot.request.DemoRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class DemoController {
    @GetMapping(value = {"/{id}/get", "/get2"})
    public String test(@PathVariable Optional<Integer> id,
                       @RequestParam String parameter1,
                       @RequestParam(required = false) Color color) {

        String stringId = id.isPresent() ? id.get().toString() : "";
        String stringColor = color != null ? color.toString() : "";

        return stringId + parameter1 + stringColor;
    }

    @PostMapping("/post")
    public String test(@RequestBody DemoRequest demoRequest) {
        return demoRequest.toString();
    }
}
