package flowers;

public class Tulip extends Flower{

    private int price = 45;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Tulip";
    }
}
