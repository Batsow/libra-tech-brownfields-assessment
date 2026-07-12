package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.Loan;
import za.co.wethinkcode.model.Patron;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanTest {

    private final Book book = new Book("111", "Test Title", "Test Author");
    private final Patron patron = new Patron("P001", "Thandi Mokoena", "thandi@email.com");

    @Test
    void newLoanIsNotReturned() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        assertFalse(loan.isReturned());
    }

    @Test
    void markReturnedSetsReturnedAndReturnDate() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        loan.markReturned(LocalDate.of(2026, 1, 10));
        assertTrue(loan.isReturned());
        assertEquals(LocalDate.of(2026, 1, 10), loan.returnDate());
    }

    @Test
    void loanIsOverdueWhenPastDueDateAndNotReturned() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        assertTrue(loan.isOverdue(LocalDate.of(2026, 1, 20)));
        assertFalse(loan.isOverdue(LocalDate.of(2026, 1, 10)));
    }

    @Test
    void returnedLoanIsNeverOverdue() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        loan.markReturned(LocalDate.of(2026, 1, 25));
        assertFalse(loan.isOverdue(LocalDate.of(2026, 2, 1)));
    }

    @Test
    void daysOverdueCountsCorrectly() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        assertEquals(5, loan.daysOverdue(LocalDate.of(2026, 1, 20)));
    }

    @Test
    void daysOverdueIsZeroWhenNotOverdue() {
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 15));
        assertEquals(0, loan.daysOverdue(LocalDate.of(2026, 1, 10)));
    }
}
