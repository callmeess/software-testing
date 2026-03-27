package com.PrivayChat.scruber.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.PrivayChat.scruber.Enums.ScrubbingModes;
import com.PrivayChat.scruber.Interfaces.IScrub;
import com.PrivayChat.scruber.Interfaces.IScrubDigits;
import com.PrivayChat.scruber.Interfaces.IScrubEmails;

@Component
public class Scrub implements IScrub {

    private IScrubDigits scrubDigits;
    private IScrubEmails scrubEmails;

    @Autowired
    public Scrub(IScrubDigits scrubDigits, IScrubEmails scrubEmails) {
        this.scrubDigits = scrubDigits;
        this.scrubEmails = scrubEmails;
    }

    @Override
    public String scrubPrompt(String prompt, ScrubbingModes scrubbingMode) {
        if (prompt == null) {
            throw new NullPointerException("prompt and scrubbingMode must not be null");
        }
        if (prompt.trim().isEmpty()) {
            throw new IllegalArgumentException("prompt must not be blank");
        }

        if (scrubbingMode == ScrubbingModes.ONLY_DIGITS) {
            return scrubDigits.scrubDigits(prompt);
        } else if (scrubbingMode == ScrubbingModes.ONLY_EMAILS) {
            return scrubEmails.scrubEmail(prompt);
        } else if (scrubbingMode == ScrubbingModes.FULL_SCRUBBING) {
            String result =  scrubEmails.scrubEmail(prompt);
            return scrubDigits.scrubDigits(result);
        } else {
            throw new IllegalArgumentException("Invalid scrubbing mode");
        }
    }
    
}
