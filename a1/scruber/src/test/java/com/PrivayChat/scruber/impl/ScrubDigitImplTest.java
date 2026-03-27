package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScrubDigitImplTest {
    private final scrubDigitImpl scrubber = new scrubDigitImpl();

    @Test
    void testScrubDigits_HappyPath() {
        // Arrange
        String input = "Call me at 123-456-7890.";
        String expected = "Call me at XXX-XXX-XXXX.";

        // Act
        String actual = scrubber.scrubDigits(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testScrubDigits_EmptyString() {
        // Arrange
        String input = " ";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_NullInput() {
        // Arrange
        String input = null;

        // Act & Assert
        assertThrows(NullPointerException.class, () -> scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_PriceNotScrubbed() {
        // Arrange
        String input = "The price is 100$ and phone 555-1234.";
        String expected = "The price is 100$ and phone XXX-XXXX.";

        // Act
        String actual = scrubber.scrubDigits(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testScrubDigits_BehavioralVerification_CallCountAndParameter() {
        // Arrange
        scrubDigitImpl spyScrubber = spy(new scrubDigitImpl());
        String input = "Reach me at 1234";

        // Act
        spyScrubber.scrubDigits(input);

        // Assert
        verify(spyScrubber, times(1)).scrubDigits(input);
    }
}
