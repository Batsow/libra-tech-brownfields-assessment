package za.co.wethinkcode.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a single loan of a {@link Book} to a {@link Patron}.
 *
 * <p>Existing, fully implemented legacy code — already covered by
 * {@code LoanTest}. Do not modify.
 */
public class Loan {

    private final Book book;
    private final Patron patron;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private boolean returned;
    private LocalDate returnDate;

    public Loan(Book book, Patron patron, LocalDate checkoutDate, LocalDate dueDate) {
        this.book = book;
        this.patron = patron;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returned = false;
    }

    public Book book() {
        return book;
    }

    public Patron patron() {
        return patron;
    }

    public LocalDate checkoutDate() {
        return checkoutDate;
    }

    public LocalDate dueDate() {
        return dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public LocalDate returnDate() {
        return returnDate;
    }

    public void markReturned(LocalDate returnDate) {
        this.returned = true;
        this.returnDate = returnDate;
    }

    public boolean isOverdue(LocalDate asOf) {
        return !returned && asOf.isAfter(dueDate);
    }

    public long daysOverdue(LocalDate asOf) {
        if (!isOverdue(asOf)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(dueDate, asOf);
    }

    @Override
    public String toString() {
        return String.format("Loan[%s -> %s, due %s, returned=%b]",
                book.title(), patron.name(), dueDate, returned);
    }
}
