package za.co.wethinkcode.model;

/**
 * Represents a single book in the library catalogue.
 *
 * <p>This is existing, working legacy code — it is fully implemented
 * already and covered by {@code BookTest}. Do NOT change anything
 * above the marked extension point; the given tests rely on the
 * current behaviour and constructor.
 *
 * <p>You will extend this class in Step 3 to support the new
 * {@link LoanPolicy} abstraction, without breaking anything that
 * already works.
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    private boolean available;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String isbn() {
        return isbn;
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed() {
        this.available = false;
    }

    public void markAvailable() {
        this.available = true;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s) [%s]", title, author, isbn,
                available ? "Available" : "On loan");
    }

    // ================================================================
    // BROWNFIELD EXTENSION POINT — Step 3
    //
    // The library now stocks Reference and Media items alongside
    // Standard books, each with different loan rules (see LoanPolicy).
    // Add support for an OPTIONAL LoanPolicy on a Book WITHOUT
    // changing anything above this line or breaking BookTest.
    //
    // TODO:
    //   1. Declare a private LoanPolicy field.
    //   2. Add a second constructor: Book(isbn, title, author, loanPolicy)
    //      that reuses the existing constructor (this(...)) and then
    //      stores the policy.
    //   3. Implement loanPolicy() — if no policy was supplied (i.e.
    //      the book was built with the original 3-arg constructor),
    //      return a new StandardLoanPolicy() instead of null, so
    //      older code keeps working unchanged.
    //   4. Implement setLoanPolicy(LoanPolicy) so a policy can be
    //      attached after construction.
    // ================================================================

    private LoanPolicy loanPolicy; // TODO: populate via constructor/setter below

    public Book(String isbn, String title, String author, LoanPolicy loanPolicy) {
        this(isbn, title, author);
        // TODO: store loanPolicy
    }

    public LoanPolicy loanPolicy() {
        // TODO: return loanPolicy, defaulting to StandardLoanPolicy when unset
        return null;
    }

    public void setLoanPolicy(LoanPolicy loanPolicy) {
        // TODO: implement
    }
}
