
package com.PrivayChat.scruber.impl;

import java.util.regex.Pattern;

import com.PrivayChat.scruber.Interfaces.IScrubDigits;


import org.springframework.stereotype.Component;

@Component
public class scrubDigitImpl implements IScrubDigits {

    // Replace a digit only when it is not part of a number immediately followed by '$'.
    private static final Pattern SCRUBBABLE_DIGIT_PATTERN =
            Pattern.compile("\\d(?![\\d,]*(?:\\.\\d+)?\\$)");

    @Override
    public String scrubDigits(String prompt) {
        if (prompt == null) {
            throw new NullPointerException("prompt must not be null");
        }
        if (prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("prompt must not be blank");
        }

        return SCRUBBABLE_DIGIT_PATTERN.matcher(prompt).replaceAll("X");
    }

    
}