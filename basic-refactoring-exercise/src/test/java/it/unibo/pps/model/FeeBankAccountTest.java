package it.unibo.pps.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeeBankAccountTest extends AbstractBankAccountTest {
    private static final double WITHDRAWAL_FEE = 1;

    @Override
    protected BankAccount createBankAccountUnderTest() {
        return new FeeBankAccount(new SimpleBankAccount(this.accountHolder, INITIAL_BALANCE), WITHDRAWAL_FEE);
    }

    @Test
    @Override
    void testWithdraw() {
        this.bankAccount.deposit(this.accountHolder.id(), DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(this.accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAWAL_AMOUNT - WITHDRAWAL_FEE, this.bankAccount.getBalance());
    }
}
