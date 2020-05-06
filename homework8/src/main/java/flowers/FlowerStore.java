package flowers;

import java.util.Arrays;

public class FlowerStore {

    public Flower[] sell(int roseNumber, int chamomileNumber, int tulipNumber) {
        Flower[] bouquet = new Flower[roseNumber + chamomileNumber + tulipNumber];
        for (int i = 0; i < bouquet.length; i++) {
            if (i < roseNumber) {
                bouquet[i] = new Rose();
            }
            if (i < chamomileNumber) {
                bouquet[i + roseNumber] = new Chamomile();
            }
            if (i < tulipNumber) {
                bouquet[i + roseNumber + chamomileNumber] = new Tulip();
            }
        }
        return bouquet;
    }

    public Flower[] sellSequence(int roseNumber, int chamomileNumber, int tulipNumber) {
        Flower[] bouquet = new Flower[roseNumber + chamomileNumber + tulipNumber];
        int i = 0;
        while (i < bouquet.length) {
            if (roseNumber > 0) {
                bouquet[i++] = new Rose();
                roseNumber--;
            }
            if (chamomileNumber > 0) {
                bouquet[i++] = new Chamomile();
                chamomileNumber--;
            }
            if (tulipNumber > 0) {
                bouquet[i++] = new Tulip();
                tulipNumber--;
            }
        }
        return bouquet;
    }
}
