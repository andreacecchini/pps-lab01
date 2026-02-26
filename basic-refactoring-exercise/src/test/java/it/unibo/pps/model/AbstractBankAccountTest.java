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
        this.accountHolder = new AccountHolder(ACCOUNT_HOLDER_NAME, ACCOUNT_HOLDER_SURNAME, ACCOUNT_HOLDER_ID);
        this.bankAccount = createBankAccountUnderTest();
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        this.bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotDepositWithWrongId() {
        final double wrongDepositAmount = 50.0;
        this.bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        this.bankAccount.deposit(WRONG_ACCOUNT_HOLDER_ID, wrongDepositAmount);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testShouldNotBePossibleToDepositANegativeAmount() {
        this.bankAccount.deposit(accountHolder.id(), NEGATIVE_AMOUNT);
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        this.bankAccount.deposit(this.accountHolder.id(), DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(this.accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT - WITHDRAWAL_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void testShouldNotWithdrawWithWrongId() {
        this.bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        this.bankAccount.withdraw(WRONG_ACCOUNT_HOLDER_ID, WITHDRAWAL_AMOUNT);
        assertEquals(DEPOSIT_AMOUNT, this.bankAccount.getBalance());
    }

    @Test
    void testShouldNotWithdrawIfNotEnoughMoney() {
        this.bankAccount.withdraw(this.accountHolder.id(), WITHDRAWAL_AMOUNT);
        assertEquals(INITIAL_BALANCE, this.bankAccount.getBalance());
    }

    @Test
    void testShouldNotBePossibleToWithdrawANegativeAmount() {
        this.bankAccount.withdraw(this.accountHolder.id(), NEGATIVE_AMOUNT);
        assertEquals(INITIAL_BALANCE, this.bankAccount.getBalance());
    }

    protected abstract BankAccount createBankAccountUnderTest();
}
