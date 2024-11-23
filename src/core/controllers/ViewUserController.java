/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
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
public class ViewUserController {
    public Response viewUser(JTable usersTable) {
        try {
            // Obtener los usuarios desde el almacenamiento
            Storage storage = Storage.getInstance();
            ArrayList<User> users = storage.getUsers(); // Obtengo los usuarios

            // Modelo de la tabla
            DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
            model.setRowCount(0); // Limpia las filas existentes

            // Agregar los usuarios al modelo de la tabla
            for (User user : users) {
                model.addRow(new Object[]{
                    user.getId(),
                    user.getFirstname(),
                    user.getLastname(),
                    user.getAge()
                });
            }
            return new Response("Accounts showed successfully", Status.OK);
        } catch (Exception e) {
            // Manejo de errores
            return new Response("Error feaching or loading accounts", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
