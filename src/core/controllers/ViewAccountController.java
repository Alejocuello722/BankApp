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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */

public class ViewAccountController {

    public Response viewAccount(JTable usersTable) {
        try {
            // Obtener las cuentas desde el almacenamiento
            Storage storage = Storage.getInstance();
            ArrayList<Account> accounts = storage.getAccounts(); // Obtengo las cuentas

            // Modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            model.setRowCount(0); // Limpia las filas existentes

            // Agregar los usuarios al modelo de la tabla
            for (Account account : accounts) {
                model.addRow(new Object[]{
                    account.getId(),
                    account.getOwner().getId(),
                    account.getBalance(),
                    
                });
                
            }
            return new Response("Users showed successfully", Status.OK);
        } catch (Exception e) {
            // Manejo de errores
            return new Response("Error feaching or loading users", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
