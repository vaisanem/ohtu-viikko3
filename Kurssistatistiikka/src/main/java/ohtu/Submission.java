package ohtu;

public class Submission implements Comparable {
    private int week;
    private double hours;
    private int[] exercises;
    private int exercisesMax;
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

    public void setExerciseMax(int exercisesMax) {
        this.exercisesMax = exercisesMax;
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
        return "viikko" + week + ": tehtyjä tehtäviä yhteensä " + exercises.length + "/" + exercisesMax
                + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + exercisesToString();
    }

    @Override
    public int compareTo(Object o) {
        Submission s = (Submission) o;
        if (course.compareTo(s.getCourse())==0) return week - s.getWeek();
        return s.getCourse().compareTo(course);
    }
    
    
    
}