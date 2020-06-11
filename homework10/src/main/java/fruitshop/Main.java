package fruitshop;

import java.util.ArrayList;
import java.util.Date;

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
    }
}
