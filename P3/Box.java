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
public class Box extends Package {

    private int depth;

    public Box(int w, int h, int d) {
        super(w, h);
        depth = d;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int d) {
        depth = d;
    }

    public boolean isSuitable(int[] size) {
        //Haremos comprobacion del tamaño tambien con el objeto rotado SOBRE EL EJE
        //DE DEPTH rotando el item
        //Vamos a considerar que el eje depth es "como toca" porque sino no sabemos
        //muy bien como hariamos las comprobaciones para el envelope cuando no
        //tiene un atributo depth
        if ((size[0] <= super.getWidth()) && (size[1] <= super.getHeight()) && (size[2] <= depth) || (size[1] <= super.getWidth()) && (size[0] <= super.getHeight()) && (size[2] <= depth)) {
            return true;
        }
        return false;
    }
}
