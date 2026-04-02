package Interfaces;

import Models.ScrubMode;


public interface IScrub {
    String scrub(String input, ScrubMode mode) throws IllegalArgumentException, NullPointerException;
}