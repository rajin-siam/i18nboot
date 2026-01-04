package com.lokalise.i18nboot.controller;

import com.lokalise.i18nboot.exception.DemoException;
import com.lokalise.i18nboot.service.GreetingService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {

    private final GreetingService greetingService;
    private final MessageSource messageSource;

    public GreetingController(GreetingService greetingService, MessageSource messageSource) {
        this.greetingService = greetingService;
        this.messageSource = messageSource;
    }

    @GetMapping("{name}")
    public ResponseEntity<?> greet(
            @PathVariable String name,
            @RequestParam(defaultValue = "25") int age,
            @RequestParam(required = false) String lang,
            @RequestHeader(name = "Accept-Language", required = false) String language) {
        try {
            Locale locale = language != null ? Locale.forLanguageTag(language) : Locale.getDefault();

            greetingService.processUserInput(name, age);

            String message = messageSource.getMessage("greeting.welcome", new Object[]{name}, locale);
            String ageMessage = messageSource.getMessage("greeting.age", new Object[]{age}, locale);

            Map<String, String> response = new HashMap<>();

            response.put("message", message);
            response.put("ageInfo", ageMessage);
            return  ResponseEntity.ok(response);
        } catch (DemoException e) {
            String errorMessage = messageSource.getMessage(e.getMessageKey(), e.getArgs(), LocaleContextHolder.getLocale());
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", errorMessage);
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
