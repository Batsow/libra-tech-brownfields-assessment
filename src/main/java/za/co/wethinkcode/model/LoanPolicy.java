package za.co.wethinkcode.model;

/**
 * Defines the loan rules for a category of book.
 *
 * <p>This is the abstraction at the heart of Step 2. Instead of the
 * Library asking "what type of book is this?" and branching with
 * if/else, each {@link Book} carries a LoanPolicy and calling code
 * simply asks the policy what to do. This is the Strategy pattern,
 * and it is what lets you add a brand-new category of book later
 * without touching a single line of existing code — an example of
 * the Open/Closed Principle.
 *
 * <p>You do not need to change this interface. Implement it in
 * {@code StandardLoanPolicy}, {@code ReferenceLoanPolicy}, and
 * {@code MediaLoanPolicy}.
 */
public interface LoanPolicy {

    /** How many days a book may be borrowed for. */
    int loanPeriodDays();

    /** The fine, in Rands, charged per day overdue. */
    double dailyFineRate();

    /** Whether this category of book may leave the library at all. */
    boolean isBorrowable();

    /** A short human-readable name for this policy, e.g. "Standard". */
    String policyName();
}
