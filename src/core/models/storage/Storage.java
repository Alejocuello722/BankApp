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
    public Iterable<Account> getAccounts;
    
    private Storage() {
        this.users = new ArrayList<>();
    }
    
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    
    public boolean addUser(User user) {
        for (User u : this.users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }
    
        public boolean addAccount(Account account) {
        for (Account a: this.accounts) {
            if (a.getId().equals(account.getId())) { // Uso equals porque es String
                return false;
            }
        }
        this.accounts.add(account);
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
    // GetUsers añadido
    public ArrayList<User> getUsers() {
        return users;
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

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
