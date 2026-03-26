package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScrubEmailImplTest {
    private final scrubEmailImpl scrubber = new scrubEmailImpl();

    @Test
    void testScrubEmail_HappyPath() {
        String input = "Contact me at john.doe@example.com.";
        String expected = "Contact me at [EMAIL_HIDDEN].";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> scrubber.scrubEmail("   "));
    }

    @Test
    void testScrubEmail_NullInput() {
        assertThrows(NullPointerException.class, () -> scrubber.scrubEmail(null));
    }

    @Test
    void testScrubEmail_NoEmail() {
        String input = "No email here!";
        String expected = "No email here!";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_BehavioralVerification_CallCountAndParameter() {
        scrubEmailImpl spyScrubber = spy(new scrubEmailImpl());
        String input = "Email me: user@example.com";

        spyScrubber.scrubEmail(input);

        verify(spyScrubber, times(1)).scrubEmail(input);
    }
}
