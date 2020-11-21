/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.util.LinkedList;

/**
 *
 * @author Ojitos Rizados
 */
public class Seller extends User {

    private String accountNumber;
    private LinkedList<Item> soldItems;
    private LinkedList<Item> availableItems;

    public Seller(String name, String identifier, String password, String a) {
        super(name, identifier, password);
        accountNumber = a;
        soldItems = new LinkedList<Item>();
        availableItems = new LinkedList<Item>();
    }

    public void sell(Item i) {
        if (availableItems.contains(i) && deposit(i.getPrice())) {
            soldItems.add(i);
            availableItems.remove(i);
            System.out.println("El objeto " + i.getName() + " ha sido vendido por el vendedor " + getName() + " por un precio de " + i.getPrice() + "\n");
            return;
        }
        System.out.println("El vendedor con id " + this.getID() + " no tiene el item ingresado. O no tiene un numero de cuenta asociado.\n");
    }

    public void addAvailableItem(Item i) {
        availableItems.add(i);
    }

    public boolean deposit(double price) {
        if (!accountNumber.isEmpty()) {
            System.out.println("Deposito de " + price + " realizado en la cuenta con numero: " + accountNumber + " a nombre del usuario " + getName() + ".\n");
            return true;
        }
        return false;
    }

}
