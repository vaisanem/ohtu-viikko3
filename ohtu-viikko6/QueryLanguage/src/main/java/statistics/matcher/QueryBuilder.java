package statistics.matcher;

public class QueryBuilder {
    
    private Matcher matcher;

    public QueryBuilder() {
        initMatcher();
    }
    
    public QueryBuilder(Matcher matcher) {
        this.matcher = matcher;
    }
    
    public Matcher build() {
        QueryBuilder copy = new QueryBuilder(this.matcher);
        initMatcher();
        return copy.matcher;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(this.matcher, new HasFewerThan(value, category));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
    
    private Matcher not(Matcher matcher) {
        return new Not(matcher);
    }
    
    private void initMatcher() {
        this.matcher = new All();
    }
    
}
