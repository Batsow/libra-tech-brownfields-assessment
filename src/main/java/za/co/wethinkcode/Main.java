package za.co.wethinkcode;

import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.MediaLoanPolicy;
import za.co.wethinkcode.model.Patron;
import za.co.wethinkcode.model.ReferenceLoanPolicy;
import za.co.wethinkcode.model.StandardLoanPolicy;
import za.co.wethinkcode.service.FineCalculator;
import za.co.wethinkcode.service.Library;
import za.co.wethinkcode.service.LibraryManager;

import java.time.LocalDate;

/**
 * Demo driver. Do NOT modify this file.
 */
public class Main {

    public static void main(String[] args) {
        Library library = new Library("Braamfontein City Library");
        LibraryManager manager = new LibraryManager(library, new FineCalculator());

        library.addBook(new Book("978-0-13-468599-1", "Effective Java", "Joshua Bloch", new StandardLoanPolicy()));
        library.addBook(new Book("978-0-59-655874-2", "Design Patterns", "Gang of Four", new ReferenceLoanPolicy()));
        library.addBook(new Book("978-1-23-456789-0", "Clean Code (Audiobook)", "Robert C. Martin", new MediaLoanPolicy()));

        library.addPatron(new Patron("P001", "Thandi Mokoena", "thandi@email.com"));
        library.addPatron(new Patron("P002", "Sipho Zulu", "sipho@email.com"));

        LocalDate today = LocalDate.of(2026, 1, 5);

        manager.checkoutBook("978-0-13-468599-1", "P001", today);
        manager.checkoutBook("978-1-23-456789-0", "P002", today);

        try {
            manager.checkoutBook("978-0-59-655874-2", "P001", today);
        } catch (IllegalStateException e) {
            System.out.println("Expected failure: " + e.getMessage());
        }

        System.out.println();
        System.out.println("-- " + manager.libraryName() + " --");
        System.out.println("Books currently on loan: " + manager.booksCurrentlyOnLoan());

        LocalDate muchLater = today.plusDays(40);
        System.out.println();
        System.out.println("-- Checking status as of " + muchLater + " --");
        System.out.println("Overdue loans: " + manager.overdueLoans(muchLater).size());
        System.out.println("Total outstanding fines: R" + manager.totalOutstandingFines(muchLater));
    }
}
