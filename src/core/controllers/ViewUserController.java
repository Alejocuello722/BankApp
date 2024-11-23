/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

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
    public void viewAccountController(JTable usersTable) {
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
        } catch (Exception e) {
            // Manejo de errores
            JOptionPane.showMessageDialog(null, "Error fetching or loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
