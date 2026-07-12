package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.Loan;
import za.co.wethinkcode.model.MediaLoanPolicy;
import za.co.wethinkcode.model.Patron;
import za.co.wethinkcode.service.FineCalculator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FineCalculatorTest {

    private final FineCalculator calculator = new FineCalculator();
    private final Patron patron = new Patron("P001", "Thandi Mokoena", "thandi@email.com");

    @Test
    void noFineWhenLoanIsNotOverdue() {
        Book book = new Book("111", "Test Title", "Test Author");
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 22));
        assertEquals(0.0, calculator.calculateFine(loan, LocalDate.of(2026, 1, 10)));
    }

    @Test
    void fineUsesTheBooksDailyRateForStandardBooks() {
        Book book = new Book("111", "Test Title", "Test Author"); // defaults to Standard, R2.00/day
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 22));
        double fine = calculator.calculateFine(loan, LocalDate.of(2026, 1, 27)); // 5 days overdue
        assertEquals(10.00, fine, 0.001);
    }

    @Test
    void fineUsesTheBooksDailyRateForMediaItems() {
        Book book = new Book("222", "Some Audiobook", "Some Author", new MediaLoanPolicy()); // R5.00/day
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 8));
        double fine = calculator.calculateFine(loan, LocalDate.of(2026, 1, 11)); // 3 days overdue
        assertEquals(15.00, fine, 0.001);
    }

    @Test
    void noFineOnceLoanHasBeenReturned() {
        Book book = new Book("111", "Test Title", "Test Author");
        Loan loan = new Loan(book, patron, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 22));
        loan.markReturned(LocalDate.of(2026, 1, 30));
        assertEquals(0.0, calculator.calculateFine(loan, LocalDate.of(2026, 2, 1)));
    }
}
