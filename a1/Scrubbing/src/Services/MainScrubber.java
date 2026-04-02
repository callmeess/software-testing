package Services;

import Interfaces.*;
import Models.ScrubMode;

public class MainScrubber implements IScrub {
    private final IScrubDigits digitScrubber;
    private final IScrubEmails emailScrubber;

    public MainScrubber(IScrubDigits digitScrubber, IScrubEmails emailScrubber) {
        this.digitScrubber = digitScrubber;
        this.emailScrubber = emailScrubber;
    }

    @Override
    public String scrub(String input, ScrubMode mode) {
        if(input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        if (mode == null) {
            throw new IllegalArgumentException("ScrubMode cannot be null");
        }

        try {
            return switch (mode) {
                case ONLY_DIGITS -> digitScrubber.scrub(input);
                case ONLY_EMAILS -> emailScrubber.scrub(input);
                case FULL_SCRUBBING -> {
                    String res = emailScrubber.scrub(input);
                    yield digitScrubber.scrub(res);
                }
            };
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
