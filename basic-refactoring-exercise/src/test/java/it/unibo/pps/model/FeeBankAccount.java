package it.unibo.pps.model;

public class FeeBankAccount implements BankAccount {
    private final BankAccount bankAccount;

    public FeeBankAccount(BankAccount bankAccount, double withdrawalFee) {
        this.bankAccount = bankAccount;
    }

    @Override
    public double getBalance() {
        return this.bankAccount.getBalance();
    }

    @Override
    public void deposit(int userID, double amount) {
        this.bankAccount.deposit(userID, amount);
    }

    @Override
    public void withdraw(int userID, double amount) {
        this.bankAccount.withdraw(userID, amount);
    }
}
