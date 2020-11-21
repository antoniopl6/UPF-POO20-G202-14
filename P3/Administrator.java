/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

/**
 *
 * @author Ojitos Rizados
 */
public class Administrator extends User {

    public Administrator(String name, String identifier, String password) {
        super(name, identifier, password);
    }

    public boolean expel(User u) {
        if(u instanceof Buyer ){
            System.out.println("El administrador "+getName()+" esta expulsando al comprador "+u.getName()+".\n");
            return true;
        }
        else if(u instanceof Seller){
            System.out.println("El administrador "+getName()+" esta expulsando al vendedor "+u.getName()+".\n");
            return true;
        }
        System.out.println("Error: Un administrador no puede expulsar a otro administrador.\n");
        return false;
    }

    public boolean manageAuction(AuctionItem a, String date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);
        try
        {
            dateFormat.parse(date);
            a.setDeadline(date);
            System.out.println("La fecha límite del objeto a subasta "+a.getName()+" ha sido cambiada a la fecha de "+date+"\n");
            return true;
        }
        catch (ParseException ex)
        {
            System.out.println("No se ha podido cambiar la fecha del objeto de subasta "+a.getName()+" por que no se ha introducido una fecha valida.\n");
            return false;
        }
    }

    public void printStock(LinkedList<AuctionItem> items) {
        System.out.println("El administrador "+getName()+" esta imprimiendo el stock actual. Se tiene como resultado:\n");
        for(int i=0;i<items.size();i++){
            System.out.println((i+1)+"- "+items.get(i).getName()+", price: "+items.get(i).getPrice()+ ", con un coste de "+items.get(i).getCost()+"\n");
        }
    }

}
