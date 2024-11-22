/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank.transaction;

import core.models.bank.Account;
import core.models.bank.Account;
import core.models.bank.transaction.TransactionType;
import java.util.Date;

/**
 *
 * @author edangulo
 */
public class Transaction {
    
    private TransactionType type;
    private Account sourceAccount;
    private Account destinationAccount;
    private double amount;
    
    public Transaction(TransactionType type, Account sourceAccount, Account destinationAccount, double amount) {
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public Transaction(String fromAccountId, String toAccountId, double amountDouble, Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public double getAmount() {
        return amount;
    }
    
}
