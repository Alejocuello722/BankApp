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
        // Validar que el ID del usuario sea numérico, positivo y no exceda 9 dígitos
        int idInt;
        try {
            idInt = Integer.parseInt(idUser);
            if (idInt < 0) {
                return new Response("Id must be positive", Status.BAD_REQUEST);
            }
            if (idUser.length() > 9) {
                return new Response("ID must have at most 9 digits", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Id must be numeric", Status.BAD_REQUEST);
        }

        // Validar que el balance sea numérico y positivo
        int balanceInt;
        try {
            balanceInt = Integer.parseInt(balance);
            if (balanceInt < 0) {
                return new Response("Balance must be positive", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Balance must be numeric", Status.BAD_REQUEST);
        }

        // Obtener instancia del almacenamiento y lista de usuarios
        Storage storage = Storage.getInstance();
        ArrayList<User> users = storage.getUsers();

        // Buscar al usuario correspondiente
        User selectedUser = null;
        for (User user : users) {
            if (user.getId() == idInt) {
                selectedUser = user;
                break;
            }
        }

        // Validar que el usuario exista
        if (selectedUser == null) {
            return new Response("User not found", Status.BAD_REQUEST);
        }

        // Generar un ID único para la cuenta
        Random random = new Random();
        int first = random.nextInt(1000);
        int second = random.nextInt(1000000);
        int third = random.nextInt(100);

        String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

        // Crear y agregar la cuenta al almacenamiento
        Account account = new Account(accountId, selectedUser, balanceInt);

        // Validar si ya existe una cuenta con el mismo ID (aunque poco probable por el formato aleatorio)
        ArrayList<Account> accounts = storage.getAccounts();
        for (Account acc : accounts) {
            if (acc.getId().equals(accountId)) {
                return new Response("An account with that ID already exists", Status.BAD_REQUEST);
            }
        }

        // Agregar la cuenta al almacenamiento
        storage.addAccount(account);

        return new Response("Account created successfully", Status.CREATED);

    } catch (Exception ex) {
        return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}


}
