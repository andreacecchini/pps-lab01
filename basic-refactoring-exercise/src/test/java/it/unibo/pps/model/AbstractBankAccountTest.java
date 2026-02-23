package it.unibo.pps.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractBankAccountTest {
    protected static final int INITIAL_BALANCE = 0;
    protected static final int DEPOSIT_AMOUNT = 100;
    protected static final int WITHDRAWAL_AMOUNT = 70;
    protected static final int NEGATIVE_AMOUNT = -1;
    protected static final double NULL_AMOUNT = 0;
    protected static final String ACCOUNT_HOLDER_NAME = "Mario";
    protected static final String ACCOUNT_HOLDER_SURNAME = "Rossi";
    protected static final int ACCOUNT_HOLDER_ID = 1;
    protected static final int WRONG_ACCOUNT_HOLDER_ID = 2;
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder(ACCOUNT_HOLDER_NAME, ACCOUNT_HOLDER_SURNAME, ACCOUNT_HOLDER_ID);
        bankAccount = createBankAccountUnderTest();
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotDepositWithWrongId() {
        final double wrongDepositAmount = 50.0;
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.deposit(WRONG_ACCOUNT_HOLDER_ID, wrongDepositAmount);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotBePossibleToDepositANegativeAmount() {
        bankAccount.deposit(accountHolder.id(), NEGATIVE_AMOUNT);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAWAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotWithdrawWithWrongId() {
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(WRONG_ACCOUNT_HOLDER_ID, WITHDRAWAL_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotWithdrawIfNotEnoughMoney() {
        bankAccount.withdraw(accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testShouldNotBePossibleToWithdrawANegativeAmount() {
        bankAccount.withdraw(accountHolder.id(), NEGATIVE_AMOUNT);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    protected abstract BankAccount createBankAccountUnderTest();
}
