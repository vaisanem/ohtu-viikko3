package ohtu;

public class Player {
    
    public final String name;
    public String points;
    public int points_won;
    private TennisPoints tennis_points;

    public Player(String name) {
        this.name = name;
        this.tennis_points = new TennisPoints();
        this.points_won = 0;
        this.points = tennis_points.getPoints(points_won);
        
    }
    
    public void wonPoint() {
        this.points_won++;
        this.points = tennis_points.getPoints(points_won);
    }
}
