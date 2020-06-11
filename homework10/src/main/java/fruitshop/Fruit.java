package fruitshop;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Fruit {
    private FruitType type;
    private int shelfLife;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;
    private int price;

    public FruitType getType() {
        return type;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public Date getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "type=" + type +
                ", shelfLife=" + shelfLife +
                ", date=" + date +
                ", price=" + price;
    }
}
