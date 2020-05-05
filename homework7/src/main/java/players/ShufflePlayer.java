package players;

public class ShufflePlayer extends Player{

    private final String[] playlist = new String[]{"First song", "Second song", "Third song", "Fourth song"};

    public ShufflePlayer(double price) {
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

    public void shuffle(){
        for (int i = 0; i < playlist.length; i++) {
            int index = (int) (Math.random() * (playlist.length - i) + i);
            String temp = playlist[i];
            playlist[i] = playlist[index];
            playlist[index] = temp;
            System.out.println("Playing: " + playlist[i]);
        }
    }
}
