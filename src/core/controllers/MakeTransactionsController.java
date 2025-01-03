/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.bank.Account;
import core.models.bank.transaction.Transaction;
import static core.models.bank.transaction.TransactionType.DEPOSIT;
import static core.models.bank.transaction.TransactionType.TRANSFER;
import static core.models.bank.transaction.TransactionType.WITHDRAW;
import core.models.storage.Storage;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class MakeTransactionsController {
    // Método para depositar dinero a una cuenta
    public static Response MakeDeposit(String accountId, String amount) {
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
            
                    // Validar existencia de la cuenta
                if (!storage.isAccountExists(accountId)) {
                    return new Response("Account not found", Status.NOT_FOUND);
                }
            Account account = null;

            // Buscar cuenta
            for (Account acc : storage.getAccounts()) {
                if (acc.getId().equals(accountId)) {
                    account = acc;
                    break;
                }
            }

            // Realizar depósito
            account.deposit(amountDouble);

            // Registrar la transacción
            storage.addTransaction(new Transaction(DEPOSIT, account, amountDouble, new Date()));
            return new Response("Deposit successful", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    

    // Método para retirar dinero de una cuenta
    public static Response MakeWithdraw(String accountId, String amount) {
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
            // Validar si la cuenta existe
            if (!storage.isAccountExists(accountId)) {
            return new Response("Account not found", Status.NOT_FOUND);
        }
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

            // Validar saldo suficiente (Validar si monto a retirar es mayor a saldo de la cuenta)
            if (amountDouble > account.getBalance()) {
                return new Response("Insufficient funds", Status.BAD_REQUEST);
            }

            // Realizar retiro
            account.withdraw(amountDouble);

            // Registrar la transacción
            storage.addTransaction(new Transaction(WITHDRAW, account, amountDouble, new Date()));
            return new Response("Withdrawal successful", Status.OK);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response MakeTransfer(String sourceAccountId, String destinationAccountId, String amount) {
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

        // Buscar las cuentas de origen y destino
        Account sourceAccount = null;
        Account destinationAccount = null;
        for (Account acc : storage.getAccounts()) {
            if (acc.getId().equals(sourceAccountId)) {
                sourceAccount = acc;
            }
            if (acc.getId().equals(destinationAccountId)) {
                destinationAccount = acc;
            }
        }

        // Validar existencia de las cuentas
        if (sourceAccount == null) {
            return new Response("Source account not found", Status.NOT_FOUND);
        }
        if (destinationAccount == null) {
            return new Response("Destination account not found", Status.NOT_FOUND);
        }

        // Realizar la transferencia usando el método transfer
        boolean success = sourceAccount.transfer(destinationAccount, amountDouble);

        if (!success) {
            return new Response("Insufficient funds", Status.BAD_REQUEST);
        }

        // Registrar las transacciones
        storage.addTransaction(new Transaction(TRANSFER, sourceAccount,destinationAccount ,amountDouble, new Date()));

        return new Response("Transfer successful", Status.OK);

    } catch (Exception ex) {
        return new Response("Unexpected error: " + ex.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}


    }



   
