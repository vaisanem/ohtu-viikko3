package ohtu;

public class Course {
    private String name;
    private String fullName;
    private String term;
    private int year;
    private int[] exercises;

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }
    
    public int getExercisesSum() {
        int s=0;
        for (int i : exercises) s+=i;
        return s;
    }
    
    @Override
    public String toString() {
        return fullName + " " + term + " " + year;
    }
}
