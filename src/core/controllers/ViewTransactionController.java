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
    public static Response viewTransaction(DefaultTableModel model) {
    try {
        // Validar que el modelo no sea nulo
        if (model == null) {
            return new Response("Table model cannot be null", Status.BAD_REQUEST);
        }

        // Obtener las transacciones desde el almacenamiento
        Storage storage = Storage.getInstance();
        ArrayList<Transaction> transactions = storage.getTransactions(); // Obtengo las transacciones

        // Validar si hay transacciones disponibles
        if (transactions == null || transactions.isEmpty()) {
            return new Response("No transactions available to show", Status.OK);
        }

        // Ordenar las transacciones de más reciente a más antigua (por fecha)
        transactions.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));

        model.setRowCount(0); // Limpia las filas existentes

        // Agregar las transacciones al modelo de la tabla con validaciones
        for (Transaction transaction : transactions) {
            // Manejo de destino nulo para evitar errores
            String destinationAccountId = transaction.getDestinationAccount() != null
                    ? transaction.getDestinationAccount().getId()
                    : "N/A";

            // Agregar al modelo de la tabla
            model.addRow(new Object[]{
                transaction.getType(),
                transaction.getSourceAccount().getId(),
                destinationAccountId,
                transaction.getAmount(),
                transaction.getDate()  // Agregar la fecha de la transacción
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
}

}

