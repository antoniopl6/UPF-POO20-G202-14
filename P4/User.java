/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toni
 */
public class User {

    private String name, identifier, password;

    public User(String n, String pass, String id) {
        name = n;
        identifier = id;
        password = pass;
        System.out.println("Usuario " + n + " con ID " + id + " registrado correctamente\n");
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String n) {
        name = n;
    }

    public boolean login(String p) {
        if (p.equals(password)) {
            return true;
        }
        return false;
    }
}
