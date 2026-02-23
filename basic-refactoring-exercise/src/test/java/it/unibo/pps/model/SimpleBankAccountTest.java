package it.unibo.pps.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private static final int INITIAL_BALANCE = 0;
    private static final int DEPOSIT_AMOUNT = 100;
    private static final int WITHDRAWAL_AMOUNT = 70;
    private static final String ACCOUNT_HOLDER_NAME = "Mario";
    private static final String ACCOUNT_HOLDER_SURNAME = "Rossi";
    private static final int ACCOUNT_HOLDER_ID = 1;
    private static final int WRONG_ACCOUNT_HOLDER_ID = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder(ACCOUNT_HOLDER_NAME, ACCOUNT_HOLDER_SURNAME, ACCOUNT_HOLDER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
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
        final double wrongDepositAmount = DEPOSIT_AMOUNT + 1.0;
        bankAccount.deposit(accountHolder.id(), DEPOSIT_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), wrongDepositAmount);
        assertEquals(DEPOSIT_AMOUNT, bankAccount.getBalance());
    }
}
