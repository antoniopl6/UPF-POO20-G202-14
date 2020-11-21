/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.util.LinkedList;

/**
 *
 * @author Ojitos Rizados
 */
public abstract class Item {

    protected String name;
    protected String type;
    protected double[] size; //tres valores para el tamaño: WidthxHeightxDepth
    protected double cost;
    protected Package pack;

    public Item() {
        name = "";
        type = "";
        size = new double[]{0, 0, 0};
        cost = 0;
        pack = new Package(0, 0);
    }

    public Item(String n, String t, double[] s, double c) {
        name = n;
        type = t;
        size = new double[3];
        //Solo queremos tres valores, cogeremos los tres primeros
        for (int i = 0; i < 3; i++) {
            size[i] = s[i];
        }
        cost = c;
        pack = new Package(0, 0);

        System.out.println("Item " + t + " " + n + " asignado correctamente\n");

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double[] getSize() {
        return size;
    }

    public double getCost() {
        return cost;
    }

    public Package getPackage() {
        return pack;
    }

    public void setName(String n) {
        name = n;
    }

    public void setType(String t) {
        type = t;
    }

    public void setSize(double[] s) {
        for (int i = 0; i < 3; i++) {
            size[i] = s[i];
        }
    }

    public void setCost(double c) {
        cost = c;
    }

    public void assignBestPackage(LinkedList<Package> lp) {
        //primero habra que comprobar la anchura del paquete
        if (size[2] <= 3) {
            //Se le debe asignar el mejor envelope, ya que la profundidas es
            //menor a 3
            //Creamos un envelope para luego guardarlo
            Envelope candidateEnvelope = new Envelope(0, 0, "");
            //Package candidatePackage = candidateEnvelope;
            //recorreremos todos los paquetes
            for (int i = 0; i < lp.size(); i++) {
                //primero comprobaremos que el paquete es un envelope
                //------------------------
                if (lp.get(i) instanceof Envelope) {
                    //ahora comprobamos que el envelope sea lo suficientemente
                    //grande como para que quepa nuestro item
                    Envelope currentEnvelope = (Envelope) lp.get(i);//downcast
                    if (currentEnvelope.isSuitable(this.convertSize(size))) {
                        //si aun no hemos guardado un evelope candidato y
                        //seguimos teniendo guardado el envelope "vacio"
                        if ((candidateEnvelope.getWidth() == 0) && (candidateEnvelope.getHeight() == 0)) {
                            //nos quedaremos con el envelope que hemos encontra-
                            //do ahora
                            candidateEnvelope = currentEnvelope;
                        }
                        //si el envolpe que hemos encontrado es menor al que ya
                        //tenemos como candidato, significa que es lo suficiente
                        //grande como para guardar el item pero es más pequeño y
                        //se ajusta mejor al tamaño del item
                        if ((currentEnvelope.getWidth() < candidateEnvelope.getWidth()) && (currentEnvelope.getHeight() < candidateEnvelope.getHeight())) {
                            candidateEnvelope = currentEnvelope;
                        }
                    }
                }
            }
            //cuando nos hemos quedado con el envelope mas optimo, hacemos un
            Package candidatePackage = candidateEnvelope;//upcast
            //con nuestro candidatePackage escogido, ya podemos asignar al item su
            //tipo de paquete
            pack = candidatePackage;
            System.out.println("Envelope " + candidateEnvelope.getName() + " asignado al item " + name + "\n");

        } else {
            //si el envelope no sirve, habra que hacer mas o menos las mismas
            //comprobaciones y quedarnos con el paquete que se acerque más a las
            //dimensiones del item
            Box candidateBox = new Box(0, 0, 0);
            //Package candidatePackage = candidateBox;
            //recorreremos todos los paquetes
            for (int i = 0; i < lp.size(); i++) {
                //primero comprobaremos que el paquete es un box
                //------------------------
                if (lp.get(i) instanceof Box) {
                    //ahora comprobamos que el envelope sea lo suficientemente
                    //grande como para que quepa nuestro item
                    Box currentBox = (Box) lp.get(i);//downcast
                    if (currentBox.isSuitable(this.convertSize(size))) {
                        //si aun no hemos guardado un box candidato y
                        //seguimos teniendo guardado el box "vacio"
                        if ((candidateBox.getWidth() == 0) && (candidateBox.getHeight() == 0) && (candidateBox.getDepth() == 0)) {
                            //nos quedaremos con el box que hemos encontra-
                            //do ahora
                            candidateBox = currentBox;
                        }
                        //si el envolpe que hemos encontrado es menor al que ya
                        //tenemos como candidato, significa que es lo suficiente
                        //grande como para guardar el item pero es más pequeño y
                        //se ajusta mejor al tamaño del item

                        if ((currentBox.getWidth() < candidateBox.getWidth()) && (currentBox.getHeight() < candidateBox.getHeight()) && (currentBox.getDepth() < candidateBox.getDepth())) {
                            candidateBox = currentBox;
                        }
                    }
                }
            }
            //cuando nos hemos quedado con el Box mas optimo, hacemos un
            Package candidatePackage = candidateBox;//upcast
            //con nuestro candidatePackage escogido, ya podemos asignar al item su
            //tipo de paquete
            pack = candidatePackage;
            System.out.println("Box de tamaño (" + candidateBox.getWidth() + ", " + candidateBox.getHeight() + ", " + candidateBox.getDepth() + ") asiganado al item " + name + "\n");
        }

    }

    public abstract double getPrice();

    public abstract double calculateProfit();

    //Funcion auxiliar, ya que para la funcion isSuitable se le pasa un array
    //de int, pero item tiene su array de tamaño en double (tambien podriamos
    //haber peusto todo a double, pero así seguiamos el esquema de la practica)
    public int[] convertSize(double[] s) {
        int[] newSize = new int[3];
        for (int i = 0; i < 3; i++) {
            newSize[i] = (int) s[i];
        }
        return newSize;
    }

}
