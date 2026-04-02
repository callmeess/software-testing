package Tests;

import org.junit.Test;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;

import Interfaces.IScrubDigits;
import Interfaces.IScrubEmails;
import Services.MainScrubber;
import Models.ScrubMode;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScrubTest {

    private final Mockery context = new JUnit4Mockery();

    @Test
    public void testScrubPrompt_EmailOnly_HappyPath() {
        // Arrange
        final IScrubDigits scrubDigits = context.mock(IScrubDigits.class);
        final IScrubEmails scrubEmails = context.mock(IScrubEmails.class);
        MainScrubber scrub = new MainScrubber(scrubDigits, scrubEmails);

        final String prompt = "Contact me at john.doe@example.com.";
        final String expected = "Contact me at [EMAIL_HIDDEN].";

        context.checking(new Expectations() {{
            oneOf(scrubEmails).scrub(prompt); will(returnValue(expected));
            never(scrubDigits).scrub(with(any(String.class)));
        }});

        // Act
        String result = scrub.scrub(prompt, ScrubMode.ONLY_EMAILS);

        // Assert
        assertThat(result, is(expected));
    }

    @Test
    public void testScrubPrompt_DigitsOnly_HappyPath() {
        // Arrange
        final IScrubDigits scrubDigits = context.mock(IScrubDigits.class);
        final IScrubEmails scrubEmails = context.mock(IScrubEmails.class);
        MainScrubber scrub = new MainScrubber(scrubDigits, scrubEmails);

        final String prompt = "Call me at 123-456-7890.";
        final String expected = "Call me at XXX-XXX-XXXX.";

        context.checking(new Expectations() {{
            oneOf(scrubDigits).scrub(prompt); will(returnValue(expected));
            never(scrubEmails).scrub(with(any(String.class)));
        }});

        // Act
        String result = scrub.scrub(prompt, ScrubMode.ONLY_DIGITS);

        // Assert
        assertThat(result, is(expected));
    }

    @Test
    public void testScrubPrompt_FullScrubbing_HappyPath() {
        // Arrange
        final IScrubDigits scrubDigits = context.mock(IScrubDigits.class);
        final IScrubEmails scrubEmails = context.mock(IScrubEmails.class);
        MainScrubber scrub = new MainScrubber(scrubDigits, scrubEmails);

        final String prompt = "Contact me at john.doe@example.com and call me at 123-456-7890.";
        final String emailScrubbed = "Contact me at [EMAIL_HIDDEN] and call me at 123-456-7890.";
        final String fullyScrubbed = "Contact me at [EMAIL_HIDDEN] and call me at XXX-XXX-XXXX.";

        context.checking(new Expectations() {{
            oneOf(scrubEmails).scrub(prompt);   will(returnValue(emailScrubbed));
            oneOf(scrubDigits).scrub(emailScrubbed); will(returnValue(fullyScrubbed));
        }});

        // Act
        String result = scrub.scrub(prompt, ScrubMode.FULL_SCRUBBING);

        // Assert
        assertThat(result, is(fullyScrubbed));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScrubPrompt_InvalidScrubbingMode() {
        // Arrange
        final IScrubDigits scrubDigits = context.mock(IScrubDigits.class);
        final IScrubEmails scrubEmails = context.mock(IScrubEmails.class);
        MainScrubber scrub = new MainScrubber(scrubDigits, scrubEmails);

        context.checking(new Expectations() {{
            never(scrubEmails).scrub(with(any(String.class)));
            never(scrubDigits).scrub(with(any(String.class)));
        }});

        // Act — expects IllegalArgumentException
        scrub.scrub("Some prompt", null);
    }
}