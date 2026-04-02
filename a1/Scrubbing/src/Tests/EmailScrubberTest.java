package Tests;

import Services.EmailScrubber;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailScrubberTest {
    private EmailScrubber scrubber = new EmailScrubber();

    // Positive (happy path) test cases
    @Test
    public void testScrubEmail_HappyPath() {
        // Arrange
        String input = "Contact me at john.doe@example.com.";
        String expected = "Contact me at [EMAIL_HIDDEN].";

        // Act
        String actual = scrubber.scrub(input);

        // Assert
        assertEquals(expected, actual);
    }

    // Negative (edge/error) test cases
    @Test
    public void testScrubEmail_EmptyString() {
        // Arrange
        String input = "   ";

        // Act & Assert
        try {
            scrubber.scrub(input);
            fail("Expected IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testScrubEmail_NullInput() {
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
    public void testScrubEmail_NoEmail() {
        // Arrange
        String input = "No email here!";
        String expected = "No email here!";

        // Act
        String actual = scrubber.scrub(input);

        // Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void testScrubEmail_MultipleEmailsInOneMessage() {
        String input = "Reach us at a@b.com or support@company.org";
        String expected = "Reach us at [EMAIL_HIDDEN] or [EMAIL_HIDDEN]";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_MixedValidAndInvalidEmailLikeTokens() {
        String input = "valid: john.doe@mail.com invalid: john@mail text@mail";
        String expected = "valid: [EMAIL_HIDDEN] invalid: john@mail text@mail";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_NoTopLevelDomain_NotScrubbed() {
        String input = "Contact: user@company";
        String expected = "Contact: user@company";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_OneLetterTopLevelDomain_NotScrubbed() {
        String input = "Contact: user@company.c";
        String expected = "Contact: user@company.c";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_MissingLocalPart_NotScrubbed() {
        String input = "Broken email: @company.com";
        String expected = "Broken email: @company.com";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_DoubleAtSymbol_NotScrubbed() {
        String input = "Broken email: user@@company.com";
        String expected = "Broken email: user@@company.com";
        assertEquals(expected, scrubber.scrub(input));
    }

    @Test
    public void testScrubEmail_InvalidDomainCharacter_NotScrubbed() {
        String input = "Broken email: admin@sub_domain.com";
        String expected = "Broken email: admin@sub_domain.com";
        assertEquals(expected, scrubber.scrub(input));
    }
  
}
