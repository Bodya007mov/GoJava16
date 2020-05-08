package flowers;

import java.io.File;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        FlowerStore store = new FlowerStore();
        System.out.println(Arrays.toString(store.sell(5, 3, 1)));
        System.out.println(store.getMoney());
        System.out.println(Arrays.toString(store.sellSequence(5, 1, 3)));
        System.out.println(store.getMoney());
        File file = new File("bouquet.json");
        FlowersSaver.save(file, store.sellSequence(5, 4, 6));
        System.out.println(Arrays.toString(FlowerLoader.load(file)));
    }
}
