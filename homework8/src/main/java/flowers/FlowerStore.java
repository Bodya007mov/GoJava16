package flowers;

public class FlowerStore {

    private int money;
    private Flower[] bouquet;

    public int getMoney() {
        return money;
    }

    public Flower[] sell(int roseNumber, int chamomileNumber, int tulipNumber) {
        bouquet = new Flower[roseNumber + chamomileNumber + tulipNumber];
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
        takeMoney();
        return bouquet;
    }

    public Flower[] sellSequence(int roseNumber, int chamomileNumber, int tulipNumber) {
        bouquet = new Flower[roseNumber + chamomileNumber + tulipNumber];
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
        takeMoney();
        return bouquet;
    }

    public void takeMoney() {
        for (Flower flower : bouquet) {
            this.money += flower.getPrice();
        }
    }
}
