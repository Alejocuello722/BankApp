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


 public class Account extends TransactionAccountMethods {
    private String id;
    private User owner;
    private double balance;

    // Constructores
    public Account(String id, User owner) {
        this(id, owner, 0); // Llama al constructor con saldo inicial
    }

    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;

        // Asocia la cuenta al usuario
        this.owner.addAccount(this);
    }

    // Getters
    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    // Implementaciones de métodos abstractos
    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public void deposit(Account account, double amount) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        account.depositToAccount(amount);
    }

    @Override
    public void depositToAccount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.balance += amount;
    }
}
