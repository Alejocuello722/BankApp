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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ViewTransactionController {
    public Response viewTransaction(JTable usersTable) {
    try {
        // Validar que la tabla no sea nula
        if (usersTable == null) {
            return new Response("Table component cannot be null", Status.BAD_REQUEST);
        }

        // Validar si el modelo de la tabla está configurado
        DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
        if (model == null) {
            return new Response("Table model is not set", Status.BAD_REQUEST);
        }

        // Obtener las transacciones desde el almacenamiento
        Storage storage = Storage.getInstance();
        ArrayList<Transaction> transactions = storage.getTransactions(); // Obtengo las transacciones

        // Validar si hay transacciones disponibles
        if (transactions == null || transactions.isEmpty()) {
            return new Response("No transactions available to show", Status.OK);
        }

        model.setRowCount(0); // Limpia las filas existentes

        // Agregar las transacciones al modelo de la tabla con validaciones
        for (Transaction transaction : transactions) {
            /*// Validaciones individuales para cada transacción
            if (transaction.getType() == null || transaction.getType().trim().isEmpty()) {
                continue; // Ignorar si el tipo es inválido
            }
            if (transaction.getSourceAccount() == null || transaction.getSourceAccount().trim().isEmpty()) {
                continue; // Ignorar si la cuenta de origen es inválida
            }
            if (transaction.getDestinationAccount() == null || transaction.getDestinationAccount().trim().isEmpty()) {
                continue; // Ignorar si la cuenta de destino es inválida
            }
            if (transaction.getAmount() <= 0) {
                continue; // Ignorar si el monto es inválido
            }
            
            */

            // Agregar al modelo si pasa todas las validaciones
            model.addRow(new Object[]{
                transaction.getType(),
                transaction.getSourceAccount(),
                transaction.getDestinationAccount(),
                transaction.getAmount()
            });
        }

        // Validar si después de todas las iteraciones no hay transacciones válidas
        if (model.getRowCount() == 0) {
            return new Response("No valid transactions to display", Status.OK);
        }

        return new Response("Transactions showed successfully", Status.OK);
    } catch (Exception e) {
        // Manejo de errores
        return new Response("Error fetching or loading transactions: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}}

