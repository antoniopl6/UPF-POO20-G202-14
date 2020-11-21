/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.util.LinkedList;

/**
 *
 * @author Toni
 */
public class Buyer extends User {

    private String accountNumber;
    LinkedList<Item> boughtItems;

    public Buyer(String n, String p, String id, String a) {
        super(n, p, id);
        accountNumber = a;
        boughtItems = new LinkedList<Item>();
    }

    public void Buy(Item i) {
        if(pay(i.getPrice())){
            System.out.println("El usuario "+getName()+" ha comprado el objeto "+i.getName()+" por un precio de "+i.getPrice()+"\n");
            boughtItems.add(i);
        }
    }

    public boolean pay(double price) {
        if(!accountNumber.isEmpty()){
            System.out.println(price+" estan siendo cobrados en la cuenta "+accountNumber+" del usuario "+this.getName()+"\n");
            return true;
        }
        System.out.println("Error: El usuario "+getName()+" no tiene un numero de cuenta asociado.\n");
        return false;
    }
}
