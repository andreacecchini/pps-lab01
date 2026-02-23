package it.unibo.pps.model;

public class FeeBankAccount implements BankAccount {
    private final BankAccount bankAccount;
    private final double withdrawalFee;

    public FeeBankAccount(BankAccount bankAccount, double withdrawalFee) {
        this.bankAccount = bankAccount;
        this.withdrawalFee = withdrawalFee;
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
        this.bankAccount.withdraw(userID, amount + this.withdrawalFee);
    }
}
