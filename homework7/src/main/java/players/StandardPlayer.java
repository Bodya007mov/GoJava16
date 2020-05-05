package players;

public class StandardPlayer extends Player{

    private final String[] playlist = new String[]{"First song", "Second song", "Third song", "Fourth song"};

    public StandardPlayer(double price) {
        super(price);
    }

    @Override
    public void playSong() {
        System.out.println("Playing: " + playlist[0]);
    }

    public void playAllSongs() {
        for (String song: playlist) {
            System.out.println("Playing: " + song);
        }
    }
}
