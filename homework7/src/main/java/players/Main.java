package players;

public class Main {

    public static void main(String[] args) {
        Player simplePlayer = new SimplePlayer(5);
        System.out.println(simplePlayer.getPRICE());
        simplePlayer.playSong();

        Player brokenPlayer = new BrokenPlayer(1.5);
        System.out.println(brokenPlayer.getPRICE());
        brokenPlayer.playSong();

        StandardPlayer standardPlayer = new StandardPlayer(8.5);
        System.out.println(standardPlayer.getPRICE());
        standardPlayer.playSong();
        standardPlayer.playAllSongs();

        ReversePlayer reversePlayer = new ReversePlayer(9.5);
        System.out.println(reversePlayer.getPRICE());
        reversePlayer.playSong();
        reversePlayer.playAllSongs();

        PlaylistReversePlayer playlistReversePlayer = new PlaylistReversePlayer(10.5);
        System.out.println(playlistReversePlayer.getPRICE());
        playlistReversePlayer.playSong();
        playlistReversePlayer.playAllSongs();

        ShufflePlayer shufflePlayer = new ShufflePlayer(15.5);
        System.out.println(shufflePlayer.getPRICE());
        shufflePlayer.playSong();
        shufflePlayer.playAllSongs();
        shufflePlayer.shuffle();
    }
}
