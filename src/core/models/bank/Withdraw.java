/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public class Withdraw implements WithdrawOperation {
    @Override
    public boolean withdraw(double amount, BalanceManager balanceManager) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount > balanceManager.getBalance()) {
            return false; // Insufficient funds
        }
        balanceManager.setBalance(balanceManager.getBalance() - amount);
        return true;
    }
}


