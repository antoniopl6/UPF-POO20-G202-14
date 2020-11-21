/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

/**
 *
 * @author Toni
 */
public class Envelope extends Package {

    private String name;

    public Envelope(int w, int h, String n) {
        super(w, h);
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public boolean isSuitable(int[] size) {
        //Haremos comprobaciones con el item "rotado" también
        if ((size[0] <= super.getWidth()) && (size[1] <= super.getHeight()) || (size[1] <= super.getWidth()) && (size[0] <= super.getHeight())) {
            return true;
        }
        return false;
    }
}
