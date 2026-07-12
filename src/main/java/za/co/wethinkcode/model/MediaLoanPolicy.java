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
        return 0;
    }

    @Override
    public double dailyFineRate() {
        // TODO
        return 0.0;
    }

    @Override
    public boolean isBorrowable() {
        // TODO
        return false;
    }

    @Override
    public String policyName() {
        // TODO
        return null;
    }
}
