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
public class WeightedItem extends Item {

    private double pricePerWeight;
    private double weight;
    private double weightRemaining;

    public WeightedItem(double pricePerWeight, double weight, String n, String t, double[] s, double c) {
        super(n, t, s, c);
        this.pricePerWeight = pricePerWeight;
        this.weight = weight;
        this.weightRemaining = weight;
        //Supondremos que el cost c es el precio original del item, mientras que
        //el pricePerWeight sera el precio por el que compra la tienda el WeightedItem,
        //que será más bajo
        //super.setCost(cost*weight);
    }

    @Override
    public double getPrice() {
        return pricePerWeight * weight;
    }

    @Override
    public double calculateProfit() {
        //coste "real", ya que pricePerWeight aquí es más barato que el precio original
        double originalCost = super.getCost() * weight;
        return originalCost - getPrice();
    }

    public double getWeightRemaining() {
        return weightRemaining;
    }

    public double sell(double w) {
        weightRemaining -= w;
        return weightRemaining;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
