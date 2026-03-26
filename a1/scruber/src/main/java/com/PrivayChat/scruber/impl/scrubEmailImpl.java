package com.PrivayChat.scruber.impl;

import com.PrivayChat.scruber.Interfaces.IScrubEmails;

public class scrubEmailImpl implements IScrubEmails {

    @Override
    public String scrubEmail(String prompt) {
        return prompt.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", "[EMAIL_HIDDEN]");
    }
    
}
