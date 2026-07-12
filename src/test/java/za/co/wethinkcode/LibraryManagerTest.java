package za.co.wethinkcode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.Loan;
import za.co.wethinkcode.model.MediaLoanPolicy;
import za.co.wethinkcode.model.Patron;
import za.co.wethinkcode.model.ReferenceLoanPolicy;
import za.co.wethinkcode.model.StandardLoanPolicy;
import za.co.wethinkcode.service.FineCalculator;
import za.co.wethinkcode.service.Library;
import za.co.wethinkcode.service.LibraryManager;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LibraryManagerTest {

    private Library library;
    private LibraryManager manager;

    @BeforeEach
    void setUp() {
        library = new Library("Test Library");
        manager = new LibraryManager(library, new FineCalculator());

        library.addBook(new Book("STD1", "Standard Book", "Author A", new StandardLoanPolicy()));
        library.addBook(new Book("REF1", "Reference Book", "Author B", new ReferenceLoanPolicy()));
        library.addBook(new Book("MED1", "Media Item", "Author C", new MediaLoanPolicy()));

        library.addPatron(new Patron("P001", "Thandi Mokoena", "thandi@email.com"));
    }

    @Test
    void libraryNameIsExposed() {
        assertEquals("Test Library", manager.libraryName());
    }

    @Test
    void checkoutBookUsesThePoliciesLoanPeriod() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        Loan loan = manager.checkoutBook("STD1", "P001", today);
        assertEquals(today.plusDays(21), loan.dueDate());
    }

    @Test
    void checkoutBookUsesMediaPolicyLoanPeriod() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        Loan loan = manager.checkoutBook("MED1", "P001", today);
        assertEquals(today.plusDays(7), loan.dueDate());
    }

    @Test
    void checkoutBookRejectsReferenceBooks() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        assertThrows(IllegalStateException.class,
                () -> manager.checkoutBook("REF1", "P001", today));
    }

    @Test
    void booksCurrentlyOnLoanCountsActiveLoans() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        manager.checkoutBook("STD1", "P001", today);
        manager.checkoutBook("MED1", "P001", today);
        assertEquals(2, manager.booksCurrentlyOnLoan());
    }

    @Test
    void returnBookReducesActiveLoanCount() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        manager.checkoutBook("STD1", "P001", today);
        manager.returnBook("STD1", today.plusDays(2));
        assertEquals(0, manager.booksCurrentlyOnLoan());
    }

    @Test
    void overdueLoansOnlyIncludesLoansPastTheirDueDate() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        manager.checkoutBook("STD1", "P001", today); // due in 21 days
        manager.checkoutBook("MED1", "P001", today); // due in 7 days

        LocalDate checkDate = today.plusDays(10);
        assertEquals(1, manager.overdueLoans(checkDate).size());
        assertEquals("MED1", manager.overdueLoans(checkDate).get(0).book().isbn());
    }

    @Test
    void totalOutstandingFinesSumsAcrossOverdueLoans() {
        LocalDate today = LocalDate.of(2026, 1, 1);
        manager.checkoutBook("STD1", "P001", today); // due in 21 days, R2.00/day
        manager.checkoutBook("MED1", "P001", today); // due in 7 days, R5.00/day

        LocalDate checkDate = today.plusDays(10); // STD1 not overdue, MED1 is 3 days overdue
        assertEquals(15.00, manager.totalOutstandingFines(checkDate), 0.001);
    }
}
