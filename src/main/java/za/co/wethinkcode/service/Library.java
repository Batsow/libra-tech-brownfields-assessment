package za.co.wethinkcode.service;

import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.Loan;
import za.co.wethinkcode.model.Patron;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The original library system. This class is existing, working
 * production code — it has been running for years and is NOT covered
 * by any automated tests. That is the brownfield problem you are
 * being asked to solve: before building anything new on top of it,
 * you need a safety net.
 *
 * <p>Do NOT modify this class. Step 1 asks you to write
 * characterization tests against it as-is; later steps build new
 * functionality around it using composition, rather than editing it.
 */
public class Library {

    public static final int DEFAULT_LOAN_DAYS = 14;

    private final String name;
    private final List<Book> catalogue = new ArrayList<>();
    private final List<Patron> patrons = new ArrayList<>();
    private final List<Loan> loans = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void addBook(Book book) {
        catalogue.add(book);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : catalogue) {
            if (book.isbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Patron findPatronById(String patronId) {
        for (Patron patron : patrons) {
            if (patron.id().equals(patronId)) {
                return patron;
            }
        }
        return null;
    }

    /** Checks out a book using the default 14-day loan period. */
    public Loan checkout(String isbn, String patronId, LocalDate today) {
        return checkout(isbn, patronId, today, DEFAULT_LOAN_DAYS);
    }

    /** Checks out a book using a caller-supplied loan period. */
    public Loan checkout(String isbn, String patronId, LocalDate today, int loanPeriodDays) {
        Book book = findBookByIsbn(isbn);
        if (book == null) {
            throw new IllegalArgumentException("No such book: " + isbn);
        }
        Patron patron = findPatronById(patronId);
        if (patron == null) {
            throw new IllegalArgumentException("No such patron: " + patronId);
        }
        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is already on loan: " + isbn);
        }

        book.markBorrowed();
        Loan loan = new Loan(book, patron, today, today.plusDays(loanPeriodDays));
        loans.add(loan);
        return loan;
    }

    public Loan returnBook(String isbn, LocalDate today) {
        Loan loan = findActiveLoan(isbn);
        if (loan == null) {
            throw new IllegalStateException("No active loan found for book: " + isbn);
        }
        loan.markReturned(today);
        loan.book().markAvailable();
        return loan;
    }

    public Loan findActiveLoan(String isbn) {
        for (Loan loan : loans) {
            if (!loan.isReturned() && loan.book().isbn().equals(isbn)) {
                return loan;
            }
        }
        return null;
    }

    public List<Loan> activeLoans() {
        List<Loan> active = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                active.add(loan);
            }
        }
        return active;
    }

    public List<Book> books() {
        return Collections.unmodifiableList(catalogue);
    }

    public List<Patron> patrons() {
        return Collections.unmodifiableList(patrons);
    }
}
