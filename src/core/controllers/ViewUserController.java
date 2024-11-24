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
import java.util.Comparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class ViewUserController {
   public static Response viewUser(DefaultTableModel model) {
    try {
        // Validar que el modelo no sea nulo
        if (model == null) {
            return new Response("Table model cannot be null", Status.BAD_REQUEST);
        }

        // Obtener los usuarios desde el almacenamiento
        Storage storage = Storage.getInstance();
        ArrayList<User> users = storage.getUsers(); // Obtengo los usuarios

        // Validar si hay usuarios disponibles
        if (users == null || users.isEmpty()) {
            return new Response("No users available to show", Status.OK);
        }

        // Ordenar los usuarios por ID usando Comparator
        users.sort(Comparator.comparing(User::getId));

        model.setRowCount(0); // Limpia las filas existentes

        // Agregar los usuarios al modelo de la tabla
        for (User user : users) {
            model.addRow(new Object[]{
                user.getId(),
                user.getFirstname() + " " + user.getLastname(), // Nombre completo
                user.getAge(), // Edad
                user.getNumAccounts()
            });
        }

        return new Response("Users showed successfully", Status.OK);
    } catch (Exception e) {
        // Manejo de errores
        return new Response("Error fetching or loading users: " + e.getMessage(), Status.INTERNAL_SERVER_ERROR);
    }
}


}
