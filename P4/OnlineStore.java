/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 *
 * @author Ojitos Rizados
 */
public class OnlineStore {

    /**
     * @param args the command line arguments
     */
    public static LinkedList<User> users;
    public static LinkedList<Item> itemsSold;
    public static LinkedList<Item> itemsAvailable;
    public static LinkedList<Package> packages;
    public static LinkedList<Sale> sales;
    public static double totalPrice;
    public static double totalProfit;

    public static void init() {
        users = new LinkedList<User>();
        itemsSold = new LinkedList<Item>();
        itemsAvailable = new LinkedList<Item>();
        packages = new LinkedList<Package>();
        sales = new LinkedList<Sale>();
        totalPrice = 0.0;
        totalProfit = 0.0;

    }

    public static Date sell(Seller aSeller, Date aDate) {
        //Reutilizamos practicamente el mismo código que implementamos en la
        //práctica anterior para simular las ventas
        for (int i = 0; i < itemsAvailable.size(); i++) {
            User actualUser = (User) users.get(i);
            //Todos los usuarios buyer compraran algo
            if (actualUser instanceof Buyer) {
                Buyer actualBuyer = (Buyer) actualUser; //downcast
                Item actualItem = itemsAvailable.get(i);
                //Habra que diferenciar entre Items Unit y Weighted
                if (actualItem instanceof UnitItem) {
                    UnitItem actualUnit = (UnitItem) actualItem;
                    if (actualUnit.getQuantityRemaining() > 0) {
                        actualBuyer.Buy(actualItem);
                        actualUnit.sell(1);
                        totalPrice += actualItem.getPricePlusTax();
                    }
                } else if (actualItem instanceof WeightedItem) {
                    WeightedItem actualWeighted = (WeightedItem) actualItem;
                    if (actualWeighted.getWeightRemaining() > 0) {
                        actualBuyer.Buy(actualItem);
                        actualWeighted.sell(1);
                        totalPrice += actualItem.getPricePlusTax();
                    }
                }
                aSeller.sell(actualItem);

                //Como fecha de envio, como no se espeficifica, aumentaremos en
                //un día más
                Calendar c = Calendar.getInstance();
                c.setTime(aDate);
                c.add(Calendar.DATE, 1);
                Date sDate = c.getTime();
                sales.add(new Sale(aDate, sDate, actualBuyer, actualItem));

                totalProfit += actualItem.calculateProfit();
                itemsSold.add(actualItem);
                itemsAvailable.remove(actualItem);
                //Para que cada Sale tenga una fecha de venta distinta,
                //usaremos la variable sDate, que tiene un dia mas que aDate
                //y la usaremos para hacer la nueva venta
                aDate = sDate;
            }
        }
        //Hemos modificado la fecha de la tienda al hacer las tiendas, así que
        //habrá que devolverlo
        return aDate;
    }

    public static Date incrementDate(Date aDate, LinkedList<AuctionItem> lAuctions, Seller aSeller) {
        //Usamos la clase Calendar para aumentar un dia al Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        c.setTime(aDate);
        c.add(Calendar.DATE, 1);
        aDate = c.getTime();
        String aDateString = dateFormat.format(aDate);
        System.out.println("La fecha a sido cambiada a " + aDateString + "\n");
        for (int i = 0; i < lAuctions.size(); i++) {
            //Compararemos solo el formato simple de fechas
            String auctionDateString = dateFormat.format(lAuctions.get(i).getDeadline());
            if (auctionDateString.equals(aDateString)) {
                System.out.println("Se ha detectado una subasta que ha expirado hoy.\n");
                manageAuction(lAuctions.get(i), aSeller);
            }
        }
        return aDate;
    }

    public static void manageAuction(AuctionItem aAucIt, Seller aSeller) {
        Buyer actualBuyer = aAucIt.getBuyer();
        Date actualDate = aAucIt.getDeadline();

        actualBuyer.Buy(aAucIt);
        totalPrice += aAucIt.getPricePlusTax();
        aSeller.sell(aAucIt);

        //Como fecha de envio, como no se espeficifica, aumentaremos en
        //un día más
        Calendar c = Calendar.getInstance();
        c.setTime(actualDate);
        c.add(Calendar.DATE, 1);
        Date sDate = c.getTime();
        sales.add(new Sale(actualDate, sDate, actualBuyer, aAucIt));

        totalProfit += aAucIt.calculateProfit();
        itemsSold.add(aAucIt);
        itemsAvailable.remove(aAucIt);
        System.out.println("El objeto " + aAucIt.getName() + " ha sido vendido por el vendedor " + aSeller.getName() + " por un precio de " + aAucIt.getPrice() + "\n");
    }

    public static void main(String[] args) throws ParseException {

        init();

        //Inicializa items en itemsAvailable
        itemsAvailable.add(new UnitItem(699, 5, "Sofa", "Mobiliario", new double[]{200, 100, 90}, 800));
        itemsAvailable.add(new UnitItem(47, 1, "Mesa", "Mobiliario", new double[]{70, 100, 50}, 80));
        itemsAvailable.add(new UnitItem(15, 7, "Libro", "Papeleria", new double[]{22, 10, 2}, 22));
        itemsAvailable.add(new WeightedItem(0.60, 5, "SustratoUniversal", "Jardineria", new double[]{20, 15, 4}, 1));
        itemsAvailable.add(new WeightedItem(0.75, 2, "Arroz", "Comida", new double[]{5, 10, 5}, 0.90));

        //Inicializa usuarios
        users.add(new Buyer("Joe Black", "inthereaper", "Reaper", "123456789"));
        users.add(new Buyer("Antonia Maria", "migatito77", "AntoniaMiau", "123546789"));
        users.add(new Buyer("Enrique Tomas", "4chanlover", "4chi", "323126789"));
        users.add(new Buyer("Maria", "ksajdMiri", "Miri", "12306789"));
        users.add(new Buyer("John", "Johnny", "Rapir", "003456789"));
        users.add(new Seller("Antonio Jose de los Palomares", "askljdlkjd", "Manolo", "154546789"));
        users.add(new Administrator("Elyas Dorian", "conichiwa1515", "EdgeRed"));

        //Inicializa paquetes
        packages.add(new Envelope(29, 42, "A3"));
        packages.add(new Envelope(21, 29, "A4"));
        packages.add(new Envelope(21, 11, "A5"));
        packages.add(new Box(10, 10, 10));
        packages.add(new Box(100, 100, 100));
        packages.add(new Box(50, 150, 100));
        packages.add(new Box(200, 300, 100));

        //Antes de nada, se creará una instancia con la fecha actual, usamos la
        //clase Date que nos indica la documentacion de la práctica
        Date actualDate = new Date();
        //Con visualizar un formato de fecha más simple nos basta
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("Fecha del dia de hoy: " + dateFormat.format(actualDate) + "\n");

        //Asigar packeges, llamada a assignBestPackage
        for (int i = 0; i < itemsAvailable.size(); i++) {
            itemsAvailable.get(i).assignBestPackage(packages);
        }

        //Assignar objetos por vender al vendedor
        //Vamos a buscar el Seller de forma genérica esta vez
        Seller actualSeller = new Seller("", "", "", "");
        for (int i = 0; i < itemsAvailable.size(); i++) {
            User actualUser = (User) users.get(i);
            //Todos los usuarios buyer compraran algo
            if (actualUser instanceof Seller) {
                actualSeller = (Seller) actualUser; //downcast
            }
        }
        //Ahora asignamos los objetos que tiene que vender el Seller, como ya
        //haciamos
        for (int i = 0; i < itemsAvailable.size(); i++) {
            actualSeller.addAvailableItem(itemsAvailable.get(i));
        }

        //Compra, tenemos que pasarle el Seller ya que es una variable creada
        //en el main y si no se la pasamos no la verá sell, así no tenemos que
        //volver a buscar
        actualDate = sell(actualSeller, actualDate);

        //Simulación de Auction
        //Inicializamos Auctions
        LinkedList<AuctionItem> la = new LinkedList<AuctionItem>();
        Administrator admin = (Administrator) users.get(6);

        //Vamos a crear una fecha límite para dentro de tres semanas
        Calendar c = Calendar.getInstance();
        c.setTime(actualDate);
        c.add(Calendar.DATE, 21);
        Date auctionDate = c.getTime();
        AuctionItem actualAuctionItem = new AuctionItem(8, auctionDate, "Ordenador", "Entretenimiento", new double[]{30, 12, 4}, 1000.0);

        actualAuctionItem.assignBestPackage(packages);
        la.add(actualAuctionItem);
        actualSeller.addAvailableItem(actualAuctionItem);
        itemsAvailable.add(actualAuctionItem);

        //Vamos a añadir un AuctionItem para que expire en el proceso y tambien
        //se venda
        //Lo vamos a situar unos dias despues de actualDate y le pondremos
        //un vendedor cualquiera
        c.setTime(actualDate);
        c.add(Calendar.DATE, 1);
        auctionDate = c.getTime();
        AuctionItem actualAuctionItem2 = new AuctionItem(5, auctionDate, "Auriculares", "Electronica", new double[]{19, 9, 2}, 6.0);
        actualAuctionItem2.assignBestPackage(packages);
        la.add(actualAuctionItem2);
        actualSeller.addAvailableItem(actualAuctionItem2);
        itemsAvailable.add(actualAuctionItem2);
        actualAuctionItem2.makeBid((Buyer) users.get(0), 9.0);

        //Ahora toca gestionar un auction que aún no ha expirado
        //Primero se comprueba si hay algun AuctionItem que el dia de hoy expire
        String aDateString = dateFormat.format(actualDate);
        for (int i = 0; i < la.size(); i++) {
            //Compararemos solo el formato simple de fechas
            String auctionDateString = dateFormat.format(la.get(i).getDeadline());
            if (auctionDateString.equals(aDateString)) {
                manageAuction(la.get(i), actualSeller);
            }
        }
        //Luego se incrementa la fecha
        actualDate = incrementDate(actualDate, la, actualSeller);

        //Esta es la simulacion de un Auction de la practica anterior
        //Solo podemos hacer la simulacion de un AuctionItem a la vez, sino
        //tendriamos que trabajar con varias auction a la vez y seria
        //muy enrrevesado
        admin.printStock(la);
        if (!actualAuctionItem.frozen(actualDate)) {
            actualAuctionItem.makeBid((Buyer) users.get(0), 375.0);
        }
        //Se incrementa la fecha interna de la tienda
        actualDate = incrementDate(actualDate, la, actualSeller);

        if (!actualAuctionItem.frozen(actualDate)) {
            actualAuctionItem.makeBid((Buyer) users.get(1), 520.0);
        }
        //Vamos a cambiar la fecha, con manageAuction, como ya haciamos
        //tendrmeos que crear un nuevo Date
        c.setTime(actualDate);
        c.add(Calendar.DATE, 30);
        auctionDate = c.getTime();
        admin.manageAuction(actualAuctionItem, auctionDate);
        //Se incrementa la fecha interna de la tienda
        actualDate = incrementDate(actualDate, la, actualSeller);

        if (!actualAuctionItem.frozen(actualDate)) {
            actualAuctionItem.makeBid((Buyer) users.get(3), 500.0);
        }
        if (!actualAuctionItem.frozen(actualDate)) {
            actualAuctionItem.makeBid((Buyer) users.get(0), 650.0);
        }
        //Se incrementa la fecha interna de la tienda
        actualDate = incrementDate(actualDate, la, actualSeller);

        admin.expel(users.get(1));
        users.remove(users.get(1));
        //Cerramos ya el Auction
        admin.manageAuction(actualAuctionItem, actualDate);
        manageAuction(actualAuctionItem, actualSeller);

        //Print de items antes de ser ordenados
        System.out.println("Lista Items Available antes de ser ordenados");
        for (int i = 0; i < itemsAvailable.size(); i++) {
            System.out.println(i + 1 + "-" + itemsAvailable.get(i).name + ", price: " + itemsAvailable.get(i).getPrice() + "\n");
        }
        //Print de items despues de ser ordenados
        Collections.sort(itemsAvailable);
        System.out.println("Lista Items Available despues de ser ordenados");
        for (int i = 0; i < itemsAvailable.size(); i++) {
            System.out.println(i + 1 + "-" + itemsAvailable.get(i).name + ", price: " + itemsAvailable.get(i).getPrice() + "\n");
        }

        //Print de items vendidos antes de ser ordenados
        System.out.println("Lista Items Sold antes de ser ordenados");
        for (int i = 0; i < itemsSold.size(); i++) {
            System.out.println(i + 1 + "-" + itemsSold.get(i).name + ", price: " + itemsSold.get(i).getPrice() + "\n");
        }
        //Print de items vendidos despues de ser ordenados
        Collections.sort(itemsSold);
        System.out.println("Lista Items Sold despues de ser ordenados");
        for (int i = 0; i < itemsSold.size(); i++) {
            System.out.println(i + 1 + "-" + itemsSold.get(i).name + ", price: " + itemsSold.get(i).getPrice() + "\n");
        }

        //Sales antes de ser ordenadas
        System.out.println("Lista Sales antes de ser ordenadas");
        for (int i = 0; i < sales.size(); i++) {
            System.out.println(i + 1 + "-" + "venta del item " + sales.get(i).getItem().getName() + ", con fecha " + dateFormat.format(sales.get(i).getSaleDate()) + "\n");
        }
        //Print de items despues de ser ordenados
        Collections.sort(sales);
        System.out.println("Lista Sales despues de ser ordenadas");
        for (int i = 0; i < sales.size(); i++) {
            System.out.println(i + 1 + "-" + "venta del item " + sales.get(i).getItem().getName() + ", con fecha " + dateFormat.format(sales.get(i).getSaleDate()) + "\n");
        }

        itemsSold.add(actualAuctionItem);
        itemsAvailable.remove(actualAuctionItem);
        la.remove(actualAuctionItem);
        totalPrice += actualAuctionItem.getPrice();
        totalProfit += actualAuctionItem.calculateProfit();

        //Ultimo print del total price y total profit
        System.out.println("Total price: " + totalPrice);
        System.out.println("Total profit: " + totalProfit);
    }
}
