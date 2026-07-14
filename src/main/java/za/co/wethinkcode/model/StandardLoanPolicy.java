package za.co.wethinkcode.model;

/**
 * TODO Step 2: Standard books — the default lending category.
 *
 * <p>loanPeriodDays = 21, dailyFineRate = 2.00, isBorrowable = true,
 * policyName = "Standard"
 */
public class StandardLoanPolicy implements LoanPolicy {

    public static final int LOAN_PERIOD_DAYS = 21;
    public static final double DAILY_FINE_RATE = 2.00;

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
        return "Standard";
    }
}
