package com.PrivayChat.scruber;

import org.springframework.beans.factory.annotation.Autowired;

import com.PrivayChat.scruber.Enums.ScrubbingModes;
import com.PrivayChat.scruber.Interfaces.IScrub;
import com.PrivayChat.scruber.Interfaces.IScrubDigits;
import com.PrivayChat.scruber.Interfaces.IScrubEmails;

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
