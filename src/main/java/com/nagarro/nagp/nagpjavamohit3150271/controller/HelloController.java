package com.nagarro.nagp.nagpjavamohit3150271.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String greeting() {
        return "Hello world";
    }
}
