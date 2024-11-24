/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.bank.Account;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */

public class ViewAccountController {

   public static Response viewAccount(DefaultTableModel model) {
    try {
        // Validar que el modelo no sea nulo
        if (model == null) {
            return new Response("Table model cannot be null", Status.BAD_REQUEST);
        }

        // Obtener las cuentas desde el almacenamiento
        Storage storage = Storage.getInstance();
        ArrayList<Account> accounts = storage.getAccounts(); // Obtengo las cuentas

        // Validar si hay cuentas disponibles
        if (accounts == null || accounts.isEmpty()) {
            return new Response("No accounts available to show", Status.OK);
        }

        // Ordenar las cuentas por ID
        accounts.sort(Comparator.comparing(Account::getId));

        model.setRowCount(0); // Limpia las filas existentes

        // Agregar las cuentas al modelo de la tabla
        for (Account account : accounts) {
            model.addRow(new Object[]{
                account.getId(),
                account.getOwner().getId(),
                account.getBalance(),
            });
        }

        return new Response("Accounts showed successfully", Status.OK);
    } catch (Exception e) {
        // Manejo de errores
        return new Response("Error fetching or loading accounts: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}


}
