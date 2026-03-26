package com.PrivayChat.scruber.impl;

import com.PrivayChat.scruber.Interfaces.IScrubEmails;

public class scrubEmailImpl implements IScrubEmails {

    @Override
    public String scrubEmail(String prompt) {
        if (prompt == null) {
            throw new NullPointerException("prompt must not be null");
        }
        if (prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("prompt must not be blank");
        }

        return prompt.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "[EMAIL_HIDDEN]");
    }
    
}
