package players;

public class PlaylistReversePlayer extends Player{

    private final String[] playlist = new String[]{"First song", "Second song", "Third song", "Fourth song"};

    public PlaylistReversePlayer(double price) {
        super(price);
    }

    @Override
    public void playSong() {
        System.out.println("Playing: " + playlist[0]);
    }

    public void playAllSongs() {
        for (int i = playlist.length; i > 0; i--) {
            System.out.println("Playing: " + playlist[i - 1]);
        }
    }
}
