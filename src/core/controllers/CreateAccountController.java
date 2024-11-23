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

/**
 *
 * @author Usuario
 */
public class CreateAccountController {
public static Response createAccount(String idAccount, User owner, String balance) {
    try {
        
        int idInt= Integer.parseInt(idAccount);
        // Validar que el id siga el formato
        if (!idAccount.matches("^\\d{3}-\\d{6}-\\d{2}$")) {
    return new Response("Account ID must follow the format XXX-XXXXXX-XX", Status.BAD_REQUEST);
    }

        // Validar que el valance sea mayor a 0
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
        Account account = new Account(idAccount, owner, balanceInt); 
        
        if (!storage.addAccount(account)) {
            return new Response("An account with that ID already exists", Status.BAD_REQUEST);
        }
        // Verificar que el usuario esté registrado
        ArrayList<User> users = storage.getUsers();
        boolean userFound = false; 

        for (User owner1 : users) {
            if (owner1.getId() == idInt) { // Comparar el ID del usuario
                userFound = true; // Usuario encontrado
                break; // Salir del bucle si el usuario se encuentra
            }
        }

        if (!userFound) { // Si el usuario no fue encontrado
            return new Response("Account not found", Status.BAD_REQUEST);
        }
        

        return new Response("Account created successfully", Status.CREATED);
    } catch (Exception ex) {
        return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}

}
