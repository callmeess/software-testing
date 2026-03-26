
package com.PrivayChat.scruber.impl;

import com.PrivayChat.scruber.Interfaces.IScrubDigits;


public class scrubDigitImpl implements IScrubDigits {

    @Override
    public String scrubDigits(String prompt) {
        return prompt.replaceAll("\\d", "[DIGIT_HIDDEN]");
    }

    
}