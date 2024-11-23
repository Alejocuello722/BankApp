/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.bank.Account;
import core.models.bank.transaction.Transaction;
import core.models.person.User;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Storage {

    // Instancia Singleton
    private static Storage instance;

    // Atributos del Storage
    private ArrayList<User> users;
    private ArrayList<Account> accounts;
    private ArrayList<Transaction> transactions;

    private Storage() {
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    // Obtener instancia única
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    // Métodos de gestión de usuarios
    public boolean addUser(User user) {
        for (User u : this.users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }

    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public boolean delUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                this.users.remove(user);
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    // Métodos de gestión de cuentas
    public boolean addAccount(Account account) {
        for (Account a : this.accounts) {
            if (a.getId().equals(account.getId())) { // Uso equals porque es String
                return false;
            }
        }
        this.accounts.add(account);
        return true;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Métodos de gestión de transacciones
    public boolean addTransaction(Transaction transaction) {
        for (Transaction t : this.transactions) {
            // Comparación basada en la igualdad de las cuentas origen/destino, monto y tipo
            if (t.getSourceAccount().equals(transaction.getSourceAccount())
                && t.getDestinationAccount().equals(transaction.getDestinationAccount())
                && t.getAmount() == transaction.getAmount()
                && t.getType() == transaction.getType()) {
                return false;
            }
        }
        this.transactions.add(transaction);
        return true;
    }


    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

