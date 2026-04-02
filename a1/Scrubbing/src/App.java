import Services.EmailScrubber;
import Services.MainScrubber;
import Interfaces.IScrubDigits;
import Interfaces.IScrubEmails;
import Models.ScrubMode;

public class App {
    public static void main(String[] args) throws Exception {
        IScrubEmails emailScrubber = new EmailScrubber();
        IScrubDigits digitScrubber = new Services.DigitScrubber();
        ScrubMode mode = ScrubMode.FULL_SCRUBBING;
        MainScrubber mainScrubber = new MainScrubber(digitScrubber, emailScrubber);

        String prompt = "Contact me at ess@example.com or call me at 123-456-7890.";
        System.out.println(mainScrubber.scrub(prompt , mode));

    }
}


