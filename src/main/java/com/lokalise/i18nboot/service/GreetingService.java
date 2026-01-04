package com.lokalise.i18nboot.service;

import com.lokalise.i18nboot.exception.DemoException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GreetingService {

    public String processUserInput(String userName, int age) {
        if(userName.isEmpty()) {
            throw new DemoException("error.invalid.username", null);
        }
        if (age < 18 || age > 120) {
            throw new DemoException("error.invalid.age", new Object[]{age});
        }
        return String.format("greeting.welcome:%s", userName);
    }
}
