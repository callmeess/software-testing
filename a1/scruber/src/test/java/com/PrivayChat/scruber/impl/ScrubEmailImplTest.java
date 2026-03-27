package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScrubEmailImplTest {
    private final scrubEmailImpl scrubber = new scrubEmailImpl();

    // Positive (happy path) test cases
    @Test
    void testScrubEmail_HappyPath() {
        String input = "Contact me at john.doe@example.com.";
        String expected = "Contact me at [EMAIL_HIDDEN].";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    // Negative (edge/error) test cases
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
    void testScrubEmail_MultipleEmailsInOneMessage() {
        String input = "Reach us at a@b.com or support@company.org";
        String expected = "Reach us at [EMAIL_HIDDEN] or [EMAIL_HIDDEN]";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_MixedValidAndInvalidEmailLikeTokens() {
        String input = "valid: john.doe@mail.com invalid: john@mail text@mail";
        String expected = "valid: [EMAIL_HIDDEN] invalid: john@mail text@mail";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_NoTopLevelDomain_NotScrubbed() {
        String input = "Contact: user@company";
        String expected = "Contact: user@company";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_OneLetterTopLevelDomain_NotScrubbed() {
        String input = "Contact: user@company.c";
        String expected = "Contact: user@company.c";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_MissingLocalPart_NotScrubbed() {
        String input = "Broken email: @company.com";
        String expected = "Broken email: @company.com";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_DoubleAtSymbol_NotScrubbed() {
        String input = "Broken email: user@@company.com";
        String expected = "Broken email: user@@company.com";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    @Test
    void testScrubEmail_InvalidDomainCharacter_NotScrubbed() {
        String input = "Broken email: admin@sub_domain.com";
        String expected = "Broken email: admin@sub_domain.com";
        assertEquals(expected, scrubber.scrubEmail(input));
    }

    // Behavioral verification test case
    @Test
    void testScrubEmail_BehavioralVerification_CallCountAndParameter() {
        scrubEmailImpl spyScrubber = spy(new scrubEmailImpl());
        String input = "Email me: user@example.com";

        spyScrubber.scrubEmail(input);

        verify(spyScrubber, times(1)).scrubEmail(input);
    }

  
}
