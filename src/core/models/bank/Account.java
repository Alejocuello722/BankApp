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
    private BalanceManager balanceManager;  // Gestiona el saldo
    private DepositOperation depositOperation; // Operaciones de depósito
    private WithdrawOperation withdrawOperation; // Operaciones de retiro
    private TransferOperation transferOperation; // Operaciones de transferencia

    // Constructores
    public Account(String id, User owner) {
        this(id, owner, 0); // Llama al constructor con saldo inicial
    }

    public Account(String id, User owner, double balance) {
        this.id = id;
        this.owner = owner;
        this.balanceManager = new BalanceManagerImpl(balance);  // Instancia BalanceManager
        this.depositOperation = new Deposit();                   // Instancia DepositOperation
        this.withdrawOperation = new Withdraw();                 // Instancia WithdrawOperation
        this.transferOperation = new Transfer();                 // Instancia TransferOperation

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

    public DepositOperation getDepositOperation() {
        return depositOperation;
    }

    public WithdrawOperation getWithdrawOperation() {
        return withdrawOperation;
    }

    public TransferOperation getTransferOperation() {
        return transferOperation;
    }

    public BalanceManager getBalanceManager() {
        return balanceManager;
    }

    /**
     * Método para obtener el balance actual de la cuenta.
     */
    public double getBalance() {
        return balanceManager.getBalance(); // Delegando al BalanceManager
    }

    // Métodos de acción delegados
    public void deposit(double amount) {
        depositOperation.depositToAccount(amount, balanceManager);  // Delegando el depósito
    }

    public boolean withdraw(double amount) {
        return withdrawOperation.withdraw(amount, balanceManager);  // Delegando el retiro
    }

    public boolean transfer(Account destinationAccount, double amount) {
        return transferOperation.transfer(destinationAccount, amount, balanceManager, depositOperation);  // Delegando la transferencia
    }
}
