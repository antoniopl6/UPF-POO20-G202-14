/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private Date deadline;
    private static final double fixedFee = 5;
    private static final double percentage = 0.05;

    public AuctionItem(double startingPrice, Date deadline, String n, String t, double[] s, double c) {
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

    public boolean frozen(Date d) throws ParseException {
        if (deadline.compareTo(d) < 0) {
            return true;
        }
        return false;

    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Buyer getBuyer() {
        return bidder;
    }

    public Date getDeadline() {
        return deadline;
    }

}
