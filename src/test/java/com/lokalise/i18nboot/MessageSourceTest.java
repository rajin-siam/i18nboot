package com.lokalise.i18nboot;

import org.springframework.boot.CommandLineRunner;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceTest implements CommandLineRunner {

    private final MessageSource messageSource;

    public MessageSourceTest(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void run(String... args) throws Exception {
        String message = messageSource.getMessage("hello", null, Locale.ITALIAN);
        System.out.println(message);
    }
}
