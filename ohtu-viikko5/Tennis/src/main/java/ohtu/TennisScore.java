package ohtu;

public class TennisScore {
    
    private final Player player1;
    private final Player player2;
    private String score;
    
    private final int ENOUGH_TO_WIN = 4;
    private final int ENOUGH_DIFFERENCE = 2;
    private final String DEUCE = "Deuce";
    private final String ADVANTAGE = "Advantage ";
    private final String WIN_FOR = "Win for ";

    public TennisScore(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public String getScore() {
        if (playerWithMorePoints() == null) {
            score = tied();
        } else if (player1.points_won>=ENOUGH_TO_WIN || player2.points_won>=ENOUGH_TO_WIN) {
            score = winOrAdvantage();
        } else {
            score = player1.points + "-" + player2.points;
        }
        
        return score;
    }
    
    private Player playerWithMorePoints() {
        if (player2.points_won > player1.points_won) return player2;
        else if (player1.points_won > player2.points_won) return player1;
        else return null;
    }
    
    private String tied() {
        if (player1.points.equals("Forty")) return DEUCE;
        else return player1.points + "-All";
    }
    
    private String winOrAdvantage() {
        if (Math.abs(player1.points_won - player2.points_won) >= ENOUGH_DIFFERENCE) {
            return WIN_FOR + playerWithMorePoints().name;
        } else {
            return ADVANTAGE + playerWithMorePoints().name;
        }
    }
    
}
