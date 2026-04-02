package Services;

import java.util.regex.Pattern;

import Interfaces.IScrubDigits;

public class DigitScrubber implements IScrubDigits {

    // Replace a digit only when it is not part of a number immediately followed by '$'.
    private static final Pattern SCRUBBABLE_DIGIT_PATTERN = 
        Pattern.compile("\\d(?![\\d,]*(?:\\.\\d+)?\\$)");

    @Override
    public String scrub(String input) throws IllegalArgumentException, NullPointerException {

        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be blank");
        }
        return SCRUBBABLE_DIGIT_PATTERN.matcher(input).replaceAll("X");
    }
}