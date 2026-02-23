package it.unibo.pps.model;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest extends AbstractBankAccountTest {
    @Override
    protected BankAccount createBankAccountUnderTest() {
        return new SimpleBankAccount(this.accountHolder, INITIAL_BALANCE);
    }
}
