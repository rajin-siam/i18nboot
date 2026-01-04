package com.lokalise.i18nboot.exception;

import java.util.Objects;

public class DemoException extends RuntimeException {

    private final String messageKey;
    private final Object[] args;

    public DemoException(String messageKey, Object[] args) {
        super(messageKey);
        this.messageKey = messageKey;
        this.args = args;
    }
    public String getMessageKey() {
        return messageKey;
    }
    public Object[] getArgs() {
        return  args;
    }
}
