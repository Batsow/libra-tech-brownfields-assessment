package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Patron;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatronTest {

    @Test
    void gettersReturnConstructorValues() {
        Patron patron = new Patron("P001", "Thandi Mokoena", "thandi@email.com");
        assertEquals("P001", patron.id());
        assertEquals("Thandi Mokoena", patron.name());
        assertEquals("thandi@email.com", patron.email());
    }

    @Test
    void updateEmailChangesEmail() {
        Patron patron = new Patron("P001", "Thandi Mokoena", "thandi@email.com");
        patron.updateEmail("thandi.new@email.com");
        assertEquals("thandi.new@email.com", patron.email());
    }
}
