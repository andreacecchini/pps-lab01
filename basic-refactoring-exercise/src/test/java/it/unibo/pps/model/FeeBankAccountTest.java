package it.unibo.pps.model;

public class FeeBankAccountTest extends AbstractBankAccountTest {
    private static final double WITHDRAWAL_FEE = 1;

    @Override
    protected BankAccount createBankAccountUnderTest() {
        return new FeeBankAccount(new SimpleBankAccount(this.accountHolder, INITIAL_BALANCE), WITHDRAWAL_FEE);
    }
}
