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
            // Obtener las transacciones desde el almacenamiento
            Storage storage = Storage.getInstance();
            ArrayList<Transaction> transactions = storage.getTransactions(); // Obtengo las transacciones

            // Modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            model.setRowCount(0); // Limpia las filas existentes

            // Agregar las validaciones al modelo de la tabla
            for (Transaction transaction : transactions) {
                model.addRow(new Object[]{
                    transaction.getType(),
                    transaction.getSourceAccount(),
                    transaction.getDestinationAccount(),
                    transaction.getAmount()
                    
                });
                
            }
            return new Response("Transactions showed successfully", Status.OK);
        } catch (Exception e) {
            // Manejo de errores
            return new Response("Error feaching or loading transactions", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
