/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.auxiliars;

import core.controllers.ViewAccountController;
import core.models.person.User;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class loadUsersIntoTable {
    
    public void loadUsersIntoTable() {
    try {
        // Obtén los usuarios
        ArrayList<User> users = ViewAccountController.getAllUsers();

        // Modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) usersTable.getModel();
        model.setRowCount(0); // Limpia las filas existentes

        // Agrega los usuarios al modelo de la tabla
        for (User user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getAge()
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
