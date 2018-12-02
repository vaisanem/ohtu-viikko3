package ohtu;

import java.util.HashMap;

public class TennisPoints {
    
    public enum Points { Love, Fifteen, Thirty, Forty }
    
    public int points_won;
    private final int MAX = 3;
    private HashMap<Integer, Points> points;

    public TennisPoints() {
        this.points_won = 0;
        initPoints();
    }
    
    public String getPoints(int points_won) {
        if (points_won > MAX) return points.get(MAX).toString();
        return points.get(points_won).toString();
    }
    
    private void initPoints() {
        this.points = new HashMap<>();
        points.put(0, Points.Love);
        points.put(1, Points.Fifteen);
        points.put(2, Points.Thirty);
        points.put(3, Points.Forty);
    }
    
    

}
