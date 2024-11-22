/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.models.person.User;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ViewAccountController {
    
     public static ArrayList<User> getAllUsers() {
        try {
            Storage storage = Storage.getInstance();
            return storage.getUsers(); // Método que devuelve la lista de usuarios
        } catch (Exception ex) {
            // Manejo de errores (podrías personalizar esta parte)
            throw new RuntimeException("Error fetching users", ex);
        }
    }
    
}