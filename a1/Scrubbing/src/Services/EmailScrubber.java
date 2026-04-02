package Services;

import java.util.regex.Pattern;

import Interfaces.IScrubEmails;

public class EmailScrubber implements IScrubEmails {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");

    @Override
    public String scrub(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }
        if (input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be blank");
        }

        return EMAIL_PATTERN.matcher(input).replaceAll("[EMAIL_HIDDEN]");
    }
}
