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

/**
 *
 * @author Usuario
 */
public class CreateAccountController {
public static Response createAccount(String id, User owner, String balance) {
    try {
        // Validar ID
        int idInt;
        try {
            idInt = Integer.parseInt(id);
            if (idInt < 0) {
                return new Response("ID must be positive", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("ID must be numeric", Status.BAD_REQUEST);
        }

        // Validar Balance
        int balanceInt;
        try {
            balanceInt = Integer.parseInt(balance);
            if (balanceInt < 0) {
                return new Response("Balance must be positive", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Balance must be numeric", Status.BAD_REQUEST);
        }

        // Validar Owner
        if (owner == null) {
            return new Response("Owner cannot be null", Status.BAD_REQUEST);
        }

        // Crear cuenta
        Storage storage = Storage.getInstance();
        Account account = new Account(id, owner, balanceInt); // Verifica este constructor
        if (!storage.addAccount(account)) {
            return new Response("An account with that ID already exists", Status.BAD_REQUEST);
        }

        return new Response("Account created successfully", Status.CREATED);
    } catch (Exception ex) {
        return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}

}
