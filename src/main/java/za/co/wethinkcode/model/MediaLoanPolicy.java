package za.co.wethinkcode.model;

/**
 * TODO Step 2: Media items (DVDs, audiobooks, etc.) — shorter loan
 * period, higher daily fine.
 *
 * <p>loanPeriodDays = 7, dailyFineRate = 5.00, isBorrowable = true,
 * policyName = "Media"
 */
public class MediaLoanPolicy implements LoanPolicy {

    public static final int LOAN_PERIOD_DAYS = 7;
    public static final double DAILY_FINE_RATE = 5.00;

    @Override
    public int loanPeriodDays() {
        // TODO
        return LOAN_PERIOD_DAYS;
    }

    @Override
    public double dailyFineRate() {
        // TODO
        return DAILY_FINE_RATE;
    }

    @Override
    public boolean isBorrowable() {
        // TODO
        return true;
    }

    @Override
    public String policyName() {
        // TODO
        return "Media";
    }
}
