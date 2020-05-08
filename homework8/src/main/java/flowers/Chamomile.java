package flowers;

public class Chamomile extends Flower{

    private int price = 70;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Chamomile";
    }
}
