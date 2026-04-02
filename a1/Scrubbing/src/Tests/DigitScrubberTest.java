package Tests;

import org.junit.Test;

import Services.DigitScrubber;

import static org.junit.Assert.*;

public class DigitScrubberTest {

    private final DigitScrubber scrubber = new DigitScrubber();

    // Positive (happy path) test cases
    @Test
    public void testScrubDigits_HappyPath() {
        // Arrange
        String input = "Call me at 123-456-7890.";
        String expected = "Call me at XXX-XXX-XXXX.";

        // Act
        String actual = scrubber.scrub(input);

        // Assert
        assertEquals(expected, actual);
    }

    // Negative test cases
    @Test
    public void testScrubDigits_EmptyString() {
        // Arrange
        String input = " ";

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testScrubDigits_NullInput() {
        // Arrange
        String input = null;

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected NullPointerException");
        } catch (Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testScrubDigits_PriceNotScrubbed() {
        // Arrange
        String input = "The price is 100$ and phone 555-1234.";
        String expected = "The price is 100$ and phone XXX-XXXX.";

        // Act
        String actual = scrubber.scrub(input);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testScrubDigits_DecimalPriceNotScrubbed() {
        String input = "Total 100.50$ and id 42";
        String expected = "Total 100.50$ and id XX";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubDigits_CommaSeparatedPriceNotScrubbed() {
        String input = "Budget 1,000$ ticket 789";
        String expected = "Budget 1,000$ ticket XXX";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubDigits_SpacedDollarIsNotPriceAndShouldBeScrubbed() {
        String input = "Amount 100 $ and code 12";
        String expected = "Amount XXX $ and code XX";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubDigits_PlaceholderLikeUserTextShouldRemainUntouched() {
        String input = "Literal __PRICE_PLACEHOLDER__ and price 100$ and pin 123";
        String expected = "Literal __PRICE_PLACEHOLDER__ and price 100$ and pin XXX";
        assertEquals(expected, scrubber.scrub(input));
    }
    
}
