package za.co.wethinkcode.model;

/**
 * Represents a library patron (member).
 *
 * <p>Existing, fully implemented legacy code — already covered by
 * {@code PatronTest}. Do not modify.
 */
public class Patron {

    private final String id;
    private final String name;
    private String email;

    public Patron(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) <%s>", name, id, email);
    }
}
