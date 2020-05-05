package players;

public class ReversePlayer extends Player{

    private final String[] playlist = new String[]{"First song", "Second song", "Third song", "Fourth song"};

    public ReversePlayer(double price) {
        super(price);
    }

    @Override
    public void playSong() {
        System.out.println("Playing: " + playlist[playlist.length - 1]);
    }

    public void playAllSongs() {
        for (String song: playlist) {
            System.out.println("Playing: " + song);
        }
    }
}
