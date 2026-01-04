package com.lokalise.i18nboot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;


@Configuration
public class MessageSourceConfig {

    /**
     * MessageSource Bean Configuration
     *
     * Purpose:
     * - Load all internationalization (i18n) message files.
     * - Serve messages based on the user's Locale.
     *
     * Creation & Lifecycle:
     * - This bean is created **once** at application startup.
     * - Stored in Spring ApplicationContext (RAM) for the lifetime of the application.
     *
     * Message Cache:
     * - The internal cache stores all loaded messages in RAM for fast access.
     * - Controlled by cacheSeconds:
     *   - 3600 → cache messages for 1 hour (typical in production)
     *   - 0    → always reload messages from disk (useful in development)
     *   - -1   → cache forever; messages never reload unless app restarts
     *
     * Encoding:
     * - UTF-8 encoding ensures proper display of non-English characters (e.g., Bengali, Chinese).
     *
     * Missing Keys:
     * - If a message key is missing in the properties file, Spring will return the key itself
     *   instead of throwing an exception.
     */
    @Bean
    public MessageSource messageSource() {
        // Create a ReloadableResourceBundleMessageSource object
        var messageSource = new ReloadableResourceBundleMessageSource();

        // Set the base name of message files
        // Spring will look for:
        // - messages.properties (default)
        // - messages_en_US.properties
        // - messages_bn_BD.properties
        messageSource.setBasenames("classpath:i18n/messages");

        // Set encoding for the properties files
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

        // Set cache duration in seconds
        // - controls how often the message files are reloaded from disk
        messageSource.setCacheSeconds(3600);

        // If a key is missing in properties, use the key itself as the default message
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

}
