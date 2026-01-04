package com.lokalise.i18nboot.controller;

import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class TestController {

    private final MessageSource messageSource;

    public TestController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/test")
    public String test() {
        try {
            String welcome = messageSource.getMessage("greeting.welcome", new Object[]{"John"}, Locale.getDefault());
            String age = messageSource.getMessage("greeting.age", new Object[]{25}, Locale.getDefault());
            return "Welcome: " + welcome + ", Age: " + age;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}