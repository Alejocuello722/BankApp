/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.bank;

/**
 *
 * @author Usuario
 */
public class BalanceManagerImpl extends BalanceManager {
    private double balance;

    public BalanceManagerImpl(double balance) {
        this.balance = balance;
    }

    @Override
    protected double getBalance() {
        return balance;
    }

    @Override
    protected void setBalance(double balance) {
        this.balance = balance;
    }
}