package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.Loan;
import za.co.wethinkcode.model.LoanPolicy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO Step 5: A new layer built ON TOP OF the legacy {@link Library},
 * rather than inside it. This is the brownfield-friendly way to add a
 * feature: compose with the existing class instead of editing it.
 *
 * <p>{@code LibraryManager} makes checkout policy-aware (different
 * book categories get different loan periods, and Reference books
 * cannot be borrowed at all) and adds the reporting the business now
 * needs.
 */
public class LibraryManager {

    private final Library library;
    private final FineCalculator fineCalculator;

    public LibraryManager(Library library, FineCalculator fineCalculator) {
        this.library = library;
        this.fineCalculator = fineCalculator;
    }

    public String libraryName() {
        // TODO
        return null;
    }

    /**
     * Checks out a book, using its {@link LoanPolicy} to decide the
     * loan period, and refusing to lend Reference books at all.
     *
     * @throws IllegalArgumentException if the book does not exist
     * @throws IllegalStateException if the book's policy does not allow borrowing
     */
    public Loan checkoutBook(String isbn, String patronId, LocalDate today) {
        // TODO:
        //  1. Find the book via library.findBookByIsbn(isbn); throw
        //     IllegalArgumentException if it doesn't exist.
        //  2. Look up its LoanPolicy via book.loanPolicy().
        //  3. If policy.isBorrowable() is false, throw
        //     IllegalStateException with a helpful message.
        //  4. Otherwise call
        //     library.checkout(isbn, patronId, today, policy.loanPeriodDays())
        //     and return the result.
        return null;
    }

    public Loan returnBook(String isbn, LocalDate today) {
        // TODO: delegate to library.returnBook(isbn, today)
        return null;
    }

    public double calculateFine(Loan loan, LocalDate today) {
        // TODO: delegate to fineCalculator.calculateFine(loan, today)
        return 0.0;
    }

    public double totalOutstandingFines(LocalDate today) {
        // TODO: sum calculateFine(...) over library.activeLoans()
        return 0.0;
    }

    public List<Loan> overdueLoans(LocalDate today) {
        // TODO: filter library.activeLoans() to those where
        // loan.isOverdue(today) is true
        return new ArrayList<>();
    }

    public int booksCurrentlyOnLoan() {
        // TODO: return library.activeLoans().size()
        return 0;
    }
}
