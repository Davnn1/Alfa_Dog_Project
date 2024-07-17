package com.example.alfa.project.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import java.util.Locale;
import java.util.Map;

public class Config implements MessageSourceAware {
    private static final Map<String, Locale> supportedLanguage;
    private static MessageSource msgSource;

    static {
        supportedLanguage = Map.of("indonesian", new Locale("in"), "english", new Locale("en"));
    }

    private Config() {
    }

    public static Map<String, Locale> getSupportedLanguage() {
        return supportedLanguage;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        Config.msgSource = messageSource;
    }

    public static MessageSource getMessageSource() {
        return msgSource;
    }
}