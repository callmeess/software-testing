package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScrubEmailImplTest {
    private scrubEmailImpl scrubber = new scrubEmailImpl();

    @Test
    void testScrubEmail_HappyPath() {
        // Arrange
        String input = "Contact me at john.doe@example.com.";
        String expected = "Contact me at [EMAIL_HIDDEN].";

        // Act
        String actual = scrubber.scrubEmail(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testScrubEmail_EmptyString() {
        // Arrange
        String input = "   ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_NullInput() {
        // Arrange
        String input = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_NoEmail() {
        // Arrange
        String input = "No email here!";
        String expected = "No email here!";

        // Act
        String actual = scrubber.scrubEmail(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testScrubEmail_BehavioralVerification_CallCountAndParameter() {
        // Arrange
        scrubEmailImpl spyScrubber = spy(new scrubEmailImpl());
        String input = "Email me: user@example.com";

        // Act
        spyScrubber.scrubEmail(input);

        // Assert
        verify(spyScrubber, times(1)).scrubEmail(input);
    }
}
