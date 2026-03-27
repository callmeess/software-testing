package com.PrivayChat.scruber.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScrubDigitImplTest {
    private final scrubDigitImpl scrubber = new scrubDigitImpl();

    // Positive (happy path) test cases
    @Test
    void testScrubDigits_HappyPath() {
        String input = "Call me at 123-456-7890.";
        String expected = "Call me at XXX-XXX-XXXX.";
        assertEquals(expected, scrubber.scrubDigits(input));
    }

    // Negative test cases
    @Test
    void testScrubDigits_EmptyString() {
        assertThrows(IllegalArgumentException.class, () -> scrubber.scrubDigits(" "));
    }

    @Test
    void testScrubDigits_NullInput() {
        assertThrows(NullPointerException.class, () -> scrubber.scrubDigits(null));
    }

    @Test
    void testScrubDigits_PriceNotScrubbed() {
        String input = "The price is 100$ and phone 555-1234.";
        String expected = "The price is 100$ and phone XXX-XXXX.";
        assertEquals(expected, scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_DecimalPriceNotScrubbed() {
        String input = "Total 100.50$ and id 42";
        String expected = "Total 100.50$ and id XX";
        assertEquals(expected, scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_CommaSeparatedPriceNotScrubbed() {
        String input = "Budget 1,000$ ticket 789";
        String expected = "Budget 1,000$ ticket XXX";
        assertEquals(expected, scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_SpacedDollarIsNotPriceAndShouldBeScrubbed() {
        String input = "Amount 100 $ and code 12";
        String expected = "Amount XXX $ and code XX";
        assertEquals(expected, scrubber.scrubDigits(input));
    }

    @Test
    void testScrubDigits_PlaceholderLikeUserTextShouldRemainUntouched() {
        String input = "Literal __PRICE_PLACEHOLDER__ and price 100$ and pin 123";
        String expected = "Literal __PRICE_PLACEHOLDER__ and price 100$ and pin XXX";
        assertEquals(expected, scrubber.scrubDigits(input));
    }
    // Behavioral verification test case
    @Test
    void testScrubDigits_BehavioralVerification_CallCountAndParameter() {
        scrubDigitImpl spyScrubber = spy(new scrubDigitImpl());
        String input = "Reach me at 1234";

        spyScrubber.scrubDigits(input);

        verify(spyScrubber, times(1)).scrubDigits(input);
    }

    
}
