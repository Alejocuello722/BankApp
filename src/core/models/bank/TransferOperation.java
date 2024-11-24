/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public interface TransferOperation {
    boolean transfer(Account destinationAccount, double amount, BalanceManager balanceManager, DepositOperation depositOperation);
}
