package com.PrivayChat.scruber.impl;

import java.util.regex.Pattern;

import com.PrivayChat.scruber.Interfaces.IScrubEmails;

import org.springframework.stereotype.Component;

@Component
public class scrubEmailImpl implements IScrubEmails {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    @Override
    public String scrubEmail(String prompt) {
        if (prompt == null) {
            throw new NullPointerException("prompt must not be null");
        }
        if (prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("prompt must not be blank");
        }

        return EMAIL_PATTERN.matcher(prompt).replaceAll("[EMAIL_HIDDEN]");
    }
    
}
