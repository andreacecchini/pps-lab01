package it.unibo.pps.model;

import org.junit.jupiter.api.BeforeEach;

public class FeeBankAccountTest extends SimpleBankAccountTest {
    private static final double WITHDRAWAL_FEE = 1;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder(ACCOUNT_HOLDER_NAME, ACCOUNT_HOLDER_SURNAME, ACCOUNT_HOLDER_ID);
        bankAccount = new FeeBankAccount(new SimpleBankAccount(accountHolder, INITIAL_BALANCE), WITHDRAWAL_FEE);
    }
}
