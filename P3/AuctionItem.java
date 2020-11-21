/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ojitos Rizados
 */
public class AuctionItem extends Item {

    private double currentPrice;
    private Buyer bidder;
    private String deadline;
    private static final double fixedFee = 5;
    private static final double percentage = 0.05;

    public AuctionItem(double startingPrice, String deadline, String n, String t, double[] s, double c) {
        super(n, t, s, c);
        this.currentPrice = startingPrice;
        this.deadline = deadline;
    }

    @Override
    public double getPrice() {
        return currentPrice;
    }

    @Override
    public double calculateProfit() {
        return fixedFee + currentPrice * percentage;
    }

    public void makeBid(Buyer b, double p) {
        if (p > currentPrice) {
            bidder = b;
            currentPrice = p;
        }
    }

    public boolean frozen(String d) throws ParseException {
        Date dateInput = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        Date dateFinal = new SimpleDateFormat("dd/MM/yyyy").parse(deadline);
        //Si la fecha del input está antes que la fecha final, no esta congelado y se puede comprar.
        if (dateInput.compareTo(dateFinal) < 0) {
            return false;
        }
        return true;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Buyer getBuyer() {
        return bidder;
    }

    public String getDeadline() {
        return deadline;
    }

}
