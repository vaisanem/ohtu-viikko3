package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReaderImpl("http://nhlstats-2013-14.herokuapp.com/players.txt"));
        
        QueryBuilder query = new QueryBuilder();
        Matcher m2 = query.playsIn("EDM")
                  .hasAtLeast(30, "points").build();
        Matcher m1 = query.hasAtLeast(30, "goals")
                  .hasAtLeast(20, "assists").build();
 
        Matcher m = query.oneOf(m1, m2).build();
        for (Player player : stats.matches(m)) {
            System.out.println( player );
        }
    }
}
