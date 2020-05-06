package flowers;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        FlowerStore store = new FlowerStore();
        System.out.println(Arrays.toString(store.sell(5, 3, 1)));
        System.out.println(Arrays.toString(store.sellSequence(5, 3, 1)));
    }
}
