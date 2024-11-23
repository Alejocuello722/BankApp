/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.person.User;
import core.models.storage.Storage;

/**
 *
 * @author Usuario
 */
public class RegisterUserController {
    public static Response registerUser(String id, String firstname, String lastname, String age) {
        try {
            int idInt, ageInt;
            // Validar que sea mayor que 0 y que solo tenga 9 digitos
            try {
                idInt = Integer.parseInt(id);
                if (idInt < 0) {
                    return new Response("Id must be positive", Status.BAD_REQUEST);
                }
                if (id.length() > 9) {
            return new Response("ID must have at most 9 digits", Status.BAD_REQUEST);
        }
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }
            
            // Verificar que el nombre no sea vacío
            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                ageInt = Integer.parseInt(age);
                // Validar que la edad sea mayor a 0 y mayor a 18
                if (ageInt < 0) {
                    return new Response("Age must be positive", Status.BAD_REQUEST);
                }
                if (ageInt < 18) {
                    return new Response("Person must be older than 18", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Age must be numeric", Status.BAD_REQUEST);
            }
            
            Storage storage = Storage.getInstance();            
            if (!storage.addUser(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("A person with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Person created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
