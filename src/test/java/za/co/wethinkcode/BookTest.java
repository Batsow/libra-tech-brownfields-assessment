package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.MediaLoanPolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {

    @Test
    void newBookIsAvailableByDefault() {
        Book book = new Book("111", "Test Title", "Test Author");
        assertTrue(book.isAvailable());
    }

    @Test
    void basicGettersReturnConstructorValues() {
        Book book = new Book("111", "Test Title", "Test Author");
        assertEquals("111", book.isbn());
        assertEquals("Test Title", book.title());
        assertEquals("Test Author", book.author());
    }

    @Test
    void markBorrowedMakesBookUnavailable() {
        Book book = new Book("111", "Test Title", "Test Author");
        book.markBorrowed();
        assertFalse(book.isAvailable());
    }

    @Test
    void markAvailableMakesBookAvailableAgain() {
        Book book = new Book("111", "Test Title", "Test Author");
        book.markBorrowed();
        book.markAvailable();
        assertTrue(book.isAvailable());
    }

    @Test
    void bookWithoutExplicitPolicyDefaultsToStandard() {
        Book book = new Book("111", "Test Title", "Test Author");
        assertNotNull(book.loanPolicy());
        assertEquals("Standard", book.loanPolicy().policyName());
    }

    @Test
    void bookWithExplicitPolicyReturnsThatPolicy() {
        Book book = new Book("111", "Test Title", "Test Author", new MediaLoanPolicy());
        assertEquals("Media", book.loanPolicy().policyName());
    }

    @Test
    void setLoanPolicyChangesThePolicy() {
        Book book = new Book("111", "Test Title", "Test Author");
        book.setLoanPolicy(new MediaLoanPolicy());
        assertEquals("Media", book.loanPolicy().policyName());
    }
}
