
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
    public String scrubDigits(String Input) throws IllegalArgumentException, NullPointerException {
        if (Input == null) {
            throw new NullPointerException("Input cannot be null");
        }
        if (Input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be blank");
        }

        return SCRUBBABLE_DIGIT_PATTERN.matcher(Input).replaceAll("X");
    }

    
}