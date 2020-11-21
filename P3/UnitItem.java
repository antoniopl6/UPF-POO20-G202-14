/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

/**
 *
 * @author Ojitos Rizados
 */
public class UnitItem extends Item {

    private double unitPrice;
    private int quantity;
    private int quantityRemaining;

    public UnitItem(double unitPrice, int quantity, String n, String t, double[] s, double c) {
        super(n, t, s, c);
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.quantityRemaining = quantity;
        //Supondremos que el cost c es el precio original del item, mientras que
        //el unitPrice sera el precio por el que compra la tienda el UnitItem,
        //que será más bajo
        //super.setCost(cost*quantity);
    }

    @Override
    public double getPrice() {
        return unitPrice * quantity;
    }

    @Override
    public double calculateProfit() {
        //coste "real", ya que unitItem aquí es más barato que el precio original
        double originalCost = super.getCost() * quantity;
        return originalCost - getPrice();
    }

    public int getQuantityRemaining() {
        return quantityRemaining;
    }

    public double sell(int q) {
        quantityRemaining -= q;
        return quantityRemaining;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
