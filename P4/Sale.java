
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ojitos Rizados
 */
public class Sale implements Comparable {

    private Date saleDate;
    private Date sendDate;
    private final Buyer buyer;
    private final Item item;

    public Sale(Date saleDate, Date send, Buyer buyer, Item item) {
        this.saleDate = saleDate;
        this.buyer = buyer;
        this.item = item;
        this.sendDate = send;
    }

    public Sale(Date saleDate, Buyer buyer, Item item) {
        this.saleDate = saleDate;
        this.buyer = buyer;
        this.item = item;
    }

    public Date getSaleDate() {
        return saleDate;
    }
    
    public void setSaleDate(Date d) {
        saleDate = d;
    }
    public void setSendDate(Date d) {
        sendDate = d;
    }
    public Date getSendDate() {
        return sendDate;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public int compareTo(Object o) {
        //El objeto que entra será un Sale, para hacer
        //la comparación
        Sale s2 = (Sale) o;
        Date d1 = saleDate;
        Date d2 = s2.getSaleDate();
        return d1.compareTo(d2);
    }
}
