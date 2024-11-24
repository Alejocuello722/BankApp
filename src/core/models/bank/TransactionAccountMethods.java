/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public abstract class TransactionAccount {
    protected abstract double getBalance();
    protected abstract void setBalance(double balance);
    protected abstract void deposit(Account account, double amount);

    public void depositToAccount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        setBalance(getBalance() + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        if (amount > getBalance()) {
            return false; // Insufficient funds
        }

        setBalance(getBalance() - amount);
        return true;
    }

    public boolean transfer(Account destinationAccount, double amount) {
        if (destinationAccount == null) {
            throw new IllegalArgumentException("Destination account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (getBalance() < amount) {
            return false; // Insufficient funds
        }

        // Perform the transfer
        setBalance(getBalance() - amount);
        deposit(destinationAccount, amount);
        return true;
    }
}
