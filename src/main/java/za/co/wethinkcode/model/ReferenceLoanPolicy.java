package za.co.wethinkcode.model;

/**
 * TODO Step 2: Reference books — in-library use only, cannot be
 * borrowed at all.
 *
 * <p>loanPeriodDays = 0, dailyFineRate = 0.0, isBorrowable = false,
 * policyName = "Reference"
 */
public class ReferenceLoanPolicy implements LoanPolicy {

    public static final int LOAN_PERIOD_DAYS = 0;
    public static final double DAILY_FINE_RATE = 0.0;

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
        return false;
    }

    @Override
    public String policyName() {
        // TODO
        return "Reference";
    }
}
