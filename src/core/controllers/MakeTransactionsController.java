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
import java.util.ArrayList;
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

        // Obtener cuentas ordenadas por ID
        ArrayList<Account> accounts = storage.getAccounts();
        accounts.sort((a, b) -> a.getId().compareTo(b.getId())); // Ordenar las cuentas por ID

        // Buscar cuenta origen
        for (Account account : accounts) {
            if (account.getId().equals(fromAccountId)) {
                fromAccount = account;
                break;
            }
        }

        // Buscar cuenta destino
        for (Account account : accounts) {
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
}

// Método para depositar dinero a una cuenta
public static Response Deposit(String accountId, String amount) {
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
        Account account = null;

        // Buscar cuenta
        for (Account acc : storage.getAccounts()) {
            if (acc.getId().equals(accountId)) {
                account = acc;
                break;
            }
        }

        // Validar existencia de la cuenta
        if (account == null) {
            return new Response("Account not found", Status.NOT_FOUND);
        }

        // Realizar depósito
        account.deposit(amountDouble);

        // Registrar la transacción
        storage.addTransaction(new Transaction("DEPOSIT", accountId, amountDouble, new Date()));
        return new Response("Deposit successful", Status.OK);

    } catch (Exception ex) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}

// Método para retirar dinero de una cuenta
public static Response Withdraw(String accountId, String amount) {
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
        Account account = null;

        // Buscar cuenta
        for (Account acc : storage.getAccounts()) {
            if (acc.getId().equals(accountId)) {
                account = acc;
                break;
            }
        }

        // Validar existencia de la cuenta
        if (account == null) {
            return new Response("Account not found", Status.NOT_FOUND);
        }

        // Validar saldo suficiente
        if (amountDouble > account.getBalance()) {
            return new Response("Insufficient funds", Status.BAD_REQUEST);
        }

        // Realizar retiro
        account.withdraw(amountDouble);

        // Registrar la transacción
        storage.addTransaction(new Transaction("WITHDRAW", accountId, amountDouble, new Date()));
        return new Response("Withdrawal successful", Status.OK);

    } catch (Exception ex) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}}



   
