/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.bank.Account;
import core.models.bank.transaction.Transaction;
import core.models.storage.Storage;
import core.models.person.User;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class MakeTransactionsController {
   public static Response MakeTransaction(String fromAccountId, String toAccountId, String amount) {
    try {
        double amountDouble;

        // Validar que el monto sea numérico y positivo
        try {
            amountDouble = Double.parseDouble(amount);
            if (amountDouble <= 0) {
                return new Response("Amount must be positive", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException ex) {
            return new Response("Amount must be numeric", Status.BAD_REQUEST);
        }

        Storage storage = Storage.getInstance();
        Account fromAccount = null;
        Account toAccount = null;

        // Buscar cuenta origen
        for (Account account : storage.getAccounts) {
            if (account.getId().equals(fromAccountId)) {
                fromAccount = account;
                break;
            }
        }

        // Buscar cuenta destino
        for (Account account : storage.getAccounts) {
            if (account.getId().equals(toAccountId)) {
                toAccount = account;
                break;
            }
        }

        // Validar existencia de las cuentas
        if (fromAccount == null) {
            return new Response("Source account not found", Status.NOT_FOUND);
        }
        if (toAccount == null) {
            return new Response("Destination account not found", Status.NOT_FOUND);
        }

        // Validar saldo suficiente en la cuenta origen
        if (amountDouble > fromAccount.getBalance()) {
            return new Response("Insufficient funds in source account", Status.BAD_REQUEST);
        }

        // Realizar transferencia
        fromAccount.withdraw(amountDouble);
        toAccount.deposit(amountDouble);

        // Registrar la transacción
        storage.addTransaction(new Transaction(fromAccountId, toAccountId, amountDouble, new Date()));
        return new Response("Transfer successful", Status.OK);

    } catch (Exception ex) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}}

   
