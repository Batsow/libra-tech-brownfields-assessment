package za.co.wethinkcode;

import org.junit.jupiter.api.Test;

/**
 * STEP 1 — Characterization tests for the legacy {@code Library} class.
 *
 * <p>{@code Library} has been running in production for years with no
 * automated tests. Before anything is built on top of it (Steps 4-6),
 * you need a safety net that proves what it currently does — a
 * "characterization test": you are not testing what it SHOULD do, you
 * are pinning down what it ACTUALLY does, so that any future change
 * which breaks that behaviour gets caught immediately.
 *
 * <p>TODO: Write a test for each scenario below. Suggested method
 * names are given; fill in the body of each with a {@code Library},
 * some {@code Book}s and {@code Patron}s, and assertions against the
 * observed behaviour. Add more scenarios of your own if you find them
 * while exploring the class.
 *
 * <p>This file is marked as part of the Implementation component; it
 * is reviewed for coverage and clarity rather than an automated
 * pass/fail count, since the point of the exercise is learning to
 * read unfamiliar legacy code and pin down its behaviour, not to hit
 * a specific assertion.
 */
class LibraryCharacterizationTest {

    @Test
    void checkingOutAnAvailableBookSucceedsAndMarksItUnavailable() {
        // TODO
    }

    @Test
    void checkingOutABookThatIsAlreadyOnLoanThrows() {
        // TODO
    }

    @Test
    void checkingOutAnUnknownIsbnThrows() {
        // TODO
    }

    @Test
    void checkingOutWithAnUnknownPatronIdThrows() {
        // TODO
    }

    @Test
    void returningABookMakesItAvailableAgain() {
        // TODO
    }

    @Test
    void returningABookWithNoActiveLoanThrows() {
        // TODO
    }

    @Test
    void defaultLoanPeriodIsFourteenDaysWhenNotSpecified() {
        // TODO
    }
}
