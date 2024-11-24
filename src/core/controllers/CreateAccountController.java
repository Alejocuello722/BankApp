/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.bank.Account;
import core.models.person.User;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Usuario
 */
public class CreateAccountController {

    public static Response createAccount(String idUser, String balance) {
        try {
            // Validar ID de usuario
            int idInt = validateUserId(idUser);

            // Validar balance
            int balanceInt = validateBalance(balance);

            // Obtener usuario
            User selectedUser = findUserById(idInt);
            if (selectedUser == null) {
                return new Response("User not found", Status.BAD_REQUEST);
            }

            // Generar ID único para la cuenta
            String accountId = generateUniqueAccountId();

            // Validar y crear la cuenta
            Account account = new Account(accountId, selectedUser, balanceInt);
            if (!addAccountToStorage(account)) {
                return new Response("An account with that ID already exists", Status.BAD_REQUEST);
            }

            return new Response("Account created successfully", Status.CREATED);

        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Response("Unexpected error: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Métodos auxiliares separados por responsabilidades

    private static int validateUserId(String idUser) {
        try {
            int idInt = Integer.parseInt(idUser);
            if (idInt < 0) {
                throw new IllegalArgumentException("Id must be positive");
            }
            if (idUser.length() > 9) {
                throw new IllegalArgumentException("ID must have at most 9 digits");
            }
            return idInt;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Id must be numeric");
        }
    }

    private static int validateBalance(String balance) {
        try {
            int balanceInt = Integer.parseInt(balance);
            if (balanceInt < 0) {
                throw new IllegalArgumentException("Balance must be positive");
            }
            return balanceInt;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Balance must be numeric");
        }
    }

    private static User findUserById(int id) {
        Storage storage = Storage.getInstance();
        for (User user : storage.getUsers()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    private static String generateUniqueAccountId() {
        Random random = new Random();
        int first = random.nextInt(1000);
        int second = random.nextInt(1000000);
        int third = random.nextInt(100);
        return String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);
    }

    private static boolean addAccountToStorage(Account account) {
        Storage storage = Storage.getInstance();
        for (Account acc : storage.getAccounts()) {
            if (acc.getId().equals(account.getId())) {
                return false;
            }
        }
        storage.addAccount(account);
        return true;
    }
}

