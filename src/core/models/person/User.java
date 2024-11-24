/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.person;

import core.models.bank.Account;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author edangulo
 */


public class User {

    private final int id;
    private final String firstname;
    private final String lastname;
    private final int age;
    private final List<Account> accounts;

    public User(int id, String firstname, String lastname, int age) {
        if (id < 0) {
            throw new IllegalArgumentException("ID must be non-negative.");
        }
        if (firstname == null || firstname.trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be null or empty.");
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be null or empty.");
        }
        if (age < 18) {
            throw new IllegalArgumentException("User must be at least 18 years old.");
        }

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.accounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public int getNumAccounts() {
        return accounts.size();
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts); // Evitar modificaciones externas
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.add(account);
    }
}

