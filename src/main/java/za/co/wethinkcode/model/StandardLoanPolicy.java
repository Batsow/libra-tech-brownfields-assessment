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
