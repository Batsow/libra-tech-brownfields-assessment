package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.MediaLoanPolicy;
import za.co.wethinkcode.model.ReferenceLoanPolicy;
import za.co.wethinkcode.model.StandardLoanPolicy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoanPolicyTest {

    @Test
    void standardPolicyAllowsThreeWeeksAndIsBorrowable() {
        StandardLoanPolicy policy = new StandardLoanPolicy();
        assertEquals(21, policy.loanPeriodDays());
        assertEquals(2.00, policy.dailyFineRate());
        assertTrue(policy.isBorrowable());
        assertEquals("Standard", policy.policyName());
    }

    @Test
    void referencePolicyCannotBeBorrowed() {
        ReferenceLoanPolicy policy = new ReferenceLoanPolicy();
        assertEquals(0, policy.loanPeriodDays());
        assertEquals(0.0, policy.dailyFineRate());
        assertFalse(policy.isBorrowable());
        assertEquals("Reference", policy.policyName());
    }

    @Test
    void mediaPolicyAllowsOneWeekWithHigherFine() {
        MediaLoanPolicy policy = new MediaLoanPolicy();
        assertEquals(7, policy.loanPeriodDays());
        assertEquals(5.00, policy.dailyFineRate());
        assertTrue(policy.isBorrowable());
        assertEquals("Media", policy.policyName());
    }
}
