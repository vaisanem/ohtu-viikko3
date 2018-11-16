package ohtu;

import java.io.IOException;
import com.google.gson.*;

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
    
    public String stats() throws IOException {
        String bodytext = Main.request("https://studies.cs.helsinki.fi/courses/" + name + "/stats");
        JsonParser parser = new JsonParser();
        Gson mapper = new Gson();
        JsonObject parsittuData = parser.parse(bodytext).getAsJsonObject();
        int students = 0;
        double hours_total = 0;
        int exercises_total = 0;
        for (String key : parsittuData.keySet()) {
            WeekStatistic stat = mapper.fromJson(parsittuData.get(key), WeekStatistic.class);
            students += stat.getStudents();
            hours_total += stat.getHour_total();
            exercises_total += stat.getExercise_total();
        }
        return "kurssilla yhteensä " + students + " palautusta, palautettuja tehtäviä " + exercises_total + " kpl"
                + ", aikaa käytetty yhteensä " + hours_total + " tuntia";
    }
    
    @Override
    public String toString() {
        return fullName + " " + term + " " + year;
    }
}
