/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public class Transfer implements TransferOperation {
    @Override
    public boolean transfer(Account destinationAccount, double amount, BalanceManager balanceManager, DepositOperation depositOperation) {
        if (destinationAccount == null) {
            throw new IllegalArgumentException("Destination account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (balanceManager.getBalance() < amount) {
            return false; // Insufficient funds
        }
        // Perform the transfer
        balanceManager.setBalance(balanceManager.getBalance() - amount);
        depositOperation.depositToAccount(amount, destinationAccount.getBalanceManager());
        return true;
    }
}
