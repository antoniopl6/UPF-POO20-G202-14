/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Toni
 */
public class Package implements Taxable {

    protected int width, height;

    public Package(int w, int h) {
        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int w) {
        width = w;
    }

    public void setHeight(int h) {
        height = h;
    }

    @Override
    public double getPrice() {
        //Precio del paquete? No aparece en la documentacion ni en el seminario 4
        return this.height + this.width;
    }

    @Override
    public double getPriceOnlyTax() {
        double total = this.getPrice();
        return (total) * Item.tax;
    }

    @Override
    public double getPricePlusTax() {
        return this.getPriceOnlyTax() + this.getPrice();
    }

    @Override
    public double sumTotalTax(Taxable t) {
        return (this.getPriceOnlyTax() + t.getPriceOnlyTax());
    }

}
