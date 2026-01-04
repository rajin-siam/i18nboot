package com.lokalise.i18nboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class I18nbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(I18nbootApplication.class, args);
    }

}
