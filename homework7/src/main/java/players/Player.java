package players;

public abstract class Player {

    private final double PRICE;
    private final String[] playlist = new String[] {"Default song"};

    public Player (double price) {
        this.PRICE = price;
    }

    public double getPRICE() {
        return PRICE;
    }

    public void playSong (){
        System.out.println("Playing: " + playlist[0]);
    }
}
