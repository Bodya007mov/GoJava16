package players;

public class BrokenPlayer extends Player{

    public BrokenPlayer(double price) {
        super(price);
    }

    @Override
    public void playSong() {
        System.out.println("error");
    }
}
