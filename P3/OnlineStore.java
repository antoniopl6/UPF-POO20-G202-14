/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import java.text.ParseException;
import java.util.LinkedList;

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
    public static double totalPrice;
    public static double totalProfit;

    public static void init() {
        users = new LinkedList<User>();
        itemsSold = new LinkedList<Item>();
        itemsAvailable = new LinkedList<Item>();
        packages = new LinkedList<Package>();
        totalPrice = 0.0;
        totalProfit = 0.0;

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

        //Asigar packeges, llamada a assignBestPackage
        for (int i = 0; i < itemsAvailable.size(); i++) {
            itemsAvailable.get(i).assignBestPackage(packages);
        }

        //Assignar objetos por vender a algun vendedor
        Seller actualSeller = (Seller) users.get(5);
        for (int i = 0; i < itemsAvailable.size(); i++) {
            actualSeller.addAvailableItem(itemsAvailable.get(i));
        }

        //Compra
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
                        totalPrice += actualItem.getPrice();
                    }
                } else if (actualItem instanceof WeightedItem) {
                    WeightedItem actualWeighted = (WeightedItem) actualItem;
                    if (actualWeighted.getWeightRemaining() > 0) {
                        actualBuyer.Buy(actualItem);
                        actualWeighted.sell(1);
                        totalPrice += actualItem.getPrice();
                    }
                }
                actualSeller.sell(actualItem);
                totalProfit += actualItem.calculateProfit();
                itemsSold.add(actualItem);
                itemsAvailable.remove(actualItem);
            }
        }

        //Auction
        LinkedList<AuctionItem> la = new LinkedList<AuctionItem>();
        Administrator admin = (Administrator) users.get(6);
        AuctionItem actualAuctionItem = new AuctionItem(8, "21/11/2020", "Ordenador", "Entretenimiento", new double[]{30, 12, 4}, 1000.0);
        actualAuctionItem.assignBestPackage(packages);
        la.add(actualAuctionItem);
        actualSeller.addAvailableItem(actualAuctionItem);
        itemsAvailable.add(actualAuctionItem);

        admin.printStock(la);
        if (!actualAuctionItem.frozen("20/10/2020")) {
            actualAuctionItem.makeBid((Buyer) users.get(0), 375.0);
        }
        if (!actualAuctionItem.frozen("15/11/2020")) {
            actualAuctionItem.makeBid((Buyer) users.get(1), 520.0);
        }
        admin.manageAuction(actualAuctionItem, "25/12/2020");

        if (!actualAuctionItem.frozen("22/11/2020")) {
            actualAuctionItem.makeBid((Buyer) users.get(3), 500.0);
        }
        if (!actualAuctionItem.frozen("3/12/2020")) {
            actualAuctionItem.makeBid((Buyer) users.get(0), 650.0);
        }
        admin.expel(users.get(1));
        users.remove(users.get(0));
        Buyer auctionBuyer = actualAuctionItem.getBuyer();
        auctionBuyer.Buy(actualAuctionItem);
        actualSeller.sell(actualAuctionItem);

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
