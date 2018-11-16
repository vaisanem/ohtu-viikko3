package ohtu;

public class Submission {
    private int week;
    private double hours;
    private int[] exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }    

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }
    
    public String exercisesToString() {
        String string = "";
        for (int i=0; i<getExercises().length-1; i++) string += getExercises()[i] + ", ";
        return string + getExercises()[getExercises().length-1];
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    @Override
    public String toString() {
        return getCourse() + " viikko" + getWeek() + ": tehtyjä tehtäviä yhteensä " + getExercises().length
                + ", aikaa kului " + getHours() + " tuntia, tehdyt tehtävät: " + exercisesToString();
    }
    
}