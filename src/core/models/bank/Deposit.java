/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public class Deposit implements DepositOperation {
    @Override
    public void depositToAccount(double amount, BalanceManager balanceManager) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        balanceManager.setBalance(balanceManager.getBalance() + amount);
    }
}

