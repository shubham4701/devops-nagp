package com.nagarro.nagp.nagpjavamohit3150271.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Test
    void greeting() {
        Assert.isTrue("Hello world".equals(controller.greeting()));
    }
}