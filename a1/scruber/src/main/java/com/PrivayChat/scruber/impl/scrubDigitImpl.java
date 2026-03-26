
package com.PrivayChat.scruber.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.PrivayChat.scruber.Interfaces.IScrubDigits;


public class scrubDigitImpl implements IScrubDigits {

    private static final Pattern PRICE_PATTERN = Pattern.compile("\\b\\d+\\$");
    private static final String PRICE_PLACEHOLDER = "__PRICE_PLACEHOLDER__";

    @Override
    public String scrubDigits(String prompt) {
        if (prompt == null) {
            throw new NullPointerException("prompt must not be null");
        }
        if (prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("prompt must not be blank");
        }

        Matcher priceMatcher = PRICE_PATTERN.matcher(prompt);
        List<String> prices = new ArrayList<>();
        StringBuffer protectedPrompt = new StringBuffer();

        while (priceMatcher.find()) {
            prices.add(priceMatcher.group());
            priceMatcher.appendReplacement(protectedPrompt, PRICE_PLACEHOLDER);
        }
        priceMatcher.appendTail(protectedPrompt);

        String masked = protectedPrompt.toString().replaceAll("\\d", "X");

        for (String price : prices) {
            masked = masked.replaceFirst(PRICE_PLACEHOLDER, Matcher.quoteReplacement(price));
        }

        return masked;
    }

    
}