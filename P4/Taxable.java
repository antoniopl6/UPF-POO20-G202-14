/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ojitos Rizados
 */
public interface Taxable {

    public static final double tax = 0.21;

    public double getPrice();

    public double getPriceOnlyTax();

    public double getPricePlusTax();

    public double sumTotalTax(Taxable t);

}
