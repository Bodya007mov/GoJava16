package flowers;

public class Rose extends Flower{

    private int price = 100;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Rose";
    }
}
