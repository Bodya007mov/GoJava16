package fruitshop;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.load("firstSupply.json");
        System.out.println(shop.getStorage());

        shop.save("newSupply.json");

        shop.load("secondSupply.json");
        System.out.println(shop.getStorage());

        shop.addFruits("newSupply.json");
        System.out.println(shop.getStorage());

        try {
            System.out.println(shop.getSpoiledFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                        .parse("16/06/2020 03:00:00")));
            System.out.println(shop.getAvailableFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                        .parse("16/06/2020 03:00:00")));
            System.out.println(shop.getSpoiledFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                        .parse("20/06/2020 03:00:00"), FruitType.PINEAPPLE));
            System.out.println(shop.getAvailableFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                        .parse("20/06/2020 03:00:00"), FruitType.LEMON));
            System.out.println(shop.getAddedFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                         .parse("01/06/2020 03:00:00")));
            System.out.println(shop.getAddedFruits(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"){}
                                                         .parse("11/06/2020 03:00:00"), FruitType.MANGO));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
