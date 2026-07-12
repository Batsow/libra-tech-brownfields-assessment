package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Loan;

import java.time.LocalDate;

/**
 * TODO Step 4: Calculates the fine owed on a loan.
 *
 * <p>A loan that is not overdue owes 0.0. A loan that is overdue owes
 * (days overdue) &times; (the daily fine rate of the book's loan
 * policy).
 */
public class FineCalculator {

    public double calculateFine(Loan loan, LocalDate today) {
        // TODO: implement using loan.isOverdue(today) / loan.daysOverdue(today)
        // and loan.book().loanPolicy().dailyFineRate()
        return 0.0;
    }
}
