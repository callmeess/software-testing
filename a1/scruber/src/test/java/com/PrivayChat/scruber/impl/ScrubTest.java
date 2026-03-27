package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.PrivayChat.scruber.Enums.ScrubbingModes;
import com.PrivayChat.scruber.Interfaces.IScrubDigits;
import com.PrivayChat.scruber.Interfaces.IScrubEmails;

public class ScrubTest {

    @Test
    void testScrubPrompt_EmailOnly_HappyPath() {
        // Arrange
        IScrubDigits scrubDigits = mock(IScrubDigits.class);
        IScrubEmails scrubEmails = mock(IScrubEmails.class);
        Scrub scrub = new Scrub(scrubDigits, scrubEmails);
        String prompt = "Contact me at john.doe@example.com.";
        String expected = "Contact me at [EMAIL_HIDDEN].";
        when(scrubEmails.scrubEmail(prompt)).thenReturn(expected);

        // Act
        String result = scrub.scrubPrompt(prompt, ScrubbingModes.ONLY_EMAILS);

        // Assert
        assertEquals(expected, result);
        verify(scrubEmails, times(1)).scrubEmail(prompt);
    }

    @Test
    void testScrubPrompt_DigitsOnly_HappyPath() {
        // Arrange
        IScrubDigits scrubDigits = mock(IScrubDigits.class);
        IScrubEmails scrubEmails = mock(IScrubEmails.class);
        Scrub scrub = new Scrub(scrubDigits, scrubEmails);
        String prompt = "Call me at 123-456-7890.";
        String expected = "Call me at XXX-XXX-XXXX.";
        when(scrubDigits.scrubDigits(prompt)).thenReturn(expected);

        // Act
        String result = scrub.scrubPrompt(prompt, ScrubbingModes.ONLY_DIGITS);

        // Assert
        assertEquals(expected, result);
        verify(scrubDigits, times(1)).scrubDigits(prompt);
    }

    @Test
    void testScrubPrompt_FullScrubbing_HappyPath() {

        // Arrange
        IScrubDigits scrubDigits = mock(IScrubDigits.class);
        IScrubEmails scrubEmails = mock(IScrubEmails.class);
        Scrub scrub = new Scrub(scrubDigits, scrubEmails);

        String prompt = "Contact me at john.doe@example.com and call me at 123-456-7890.";
        String emailScrubbed = "Contact me at [EMAIL_HIDDEN] and call me at 123-456-7890.";
        String fullyScrubbed = "Contact me at [EMAIL_HIDDEN] and call me at XXX-XXX-XXXX.";
        when(scrubEmails.scrubEmail(prompt)).thenReturn(emailScrubbed);
        when(scrubDigits.scrubDigits(emailScrubbed)).thenReturn(fullyScrubbed);

        // Act
        String result = scrub.scrubPrompt(prompt, ScrubbingModes.FULL_SCRUBBING);

        // Assert
        assertEquals(fullyScrubbed, result);
        verify(scrubEmails, times(1)).scrubEmail(prompt);
        verify(scrubDigits, times(1)).scrubDigits(emailScrubbed);
    }

    @Test
    void testScrubPrompt_InvalidScrubbingMode() {
        // Arrange
        IScrubDigits scrubDigits = mock(IScrubDigits.class);
        IScrubEmails scrubEmails = mock(IScrubEmails.class);
        Scrub scrub = new Scrub(scrubDigits, scrubEmails);
        String prompt = "Some prompt";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> scrub.scrubPrompt(prompt, null));
    }


    @Test
    void testScrubPrompt_NullPrompt() {
        // Arrange
        IScrubDigits scrubDigits = mock(IScrubDigits.class);
        IScrubEmails scrubEmails = mock(IScrubEmails.class);
        Scrub scrub = new Scrub(scrubDigits, scrubEmails);

        // Act & Assert
        assertThrows(NullPointerException.class, () -> scrub.scrubPrompt(null, ScrubbingModes.ONLY_DIGITS));
    }

}
