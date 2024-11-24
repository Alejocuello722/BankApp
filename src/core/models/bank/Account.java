/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank;

import core.models.person.User;

/**
 *
 * @author edangulo
 */
public class Account {
    
    private String id;
    private User owner;
    private double balance;

    public Account(String id, User owner) {
        this.id = id;
        this.owner = owner;
        this.balance = 0;
        
        this.owner.addAccount(this);
    }
    
    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        
        this.owner.addAccount(this);
    }
    
    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        this.balance += amount;
    }
    
    public boolean withdraw(double amount) {
        if (amount > this.balance) {
            return false;
        }
        this.balance -= amount;
        return true;
    }
    
    public boolean transfer(Account destinationAccount, double amount) {
    // Validar que la cuenta de destino no sea nula
    if (destinationAccount == null) {
        throw new IllegalArgumentException("Destination account cannot be null.");
    }

    // Validar que el monto sea positivo
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be greater than zero.");
    }

    // Validar que haya saldo suficiente en la cuenta origen
    if (this.balance < amount) {
        return false; // No hay fondos suficientes
    }

    // Realizar la transferencia
    this.balance -= amount; // Descontar del saldo actual
    destinationAccount.deposit(amount); // Depositar en la cuenta de destino

    return true; // Transferencia exitosa
}

    public Object trim() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
