package com.PrivayChat.scruber.Interfaces;

public interface IScrubEmails {
    String scrubEmail(String prompt) throws IllegalArgumentException ,  NullPointerException;
}