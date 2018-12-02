package ohtu;

public class TennisGame {
    
    private final TennisScore score;
    private final Player player1;
    private final Player player2;

    public TennisGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.score = new TennisScore(player1, player2);
    }

    public void wonPoint(String playername) {
        if (player1.name.equals(playername)) player1.wonPoint();
        else player2.wonPoint();
    }

    public String getScore() {
        return score.getScore();
    }
}