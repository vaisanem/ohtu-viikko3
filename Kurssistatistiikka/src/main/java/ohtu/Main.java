package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.HashMap;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String bodyText = request(url);
        String bodyText2 = request(courseUrl);

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Arrays.sort(subs);
        Course[] ofCourses = mapper.fromJson(bodyText2, Course[].class);
        HashMap<String,Course> courses = new HashMap<>();
        for (Course c : ofCourses) courses.put(c.getName(), c);
        
        int hours=0;
        int exercises=0;
        Submission sub = subs[0];
        Course course = courses.get(sub.getCourse());
        
        System.out.println("\nopiskelijanumero: " + studentNr);
        System.out.println("\n" + course);
        sub.setExerciseMax(course.getExercises()[sub.getWeek()]);
        System.out.println(" " + sub);
        
        hours += sub.getHours();
        exercises += sub.getExercises().length;
        
        for (int i=1; i<subs.length; i++) {
            sub = subs[i];
            if (!sub.getCourse().equals(subs[i-1].getCourse())) {
                System.out.println("yhteensä: " + exercises + "/" + course.getExercisesSum() 
                        + " tehtävää, " + hours + " tuntia");
                System.out.println(course.stats());
                course = courses.get(sub.getCourse());
                exercises = hours = 0;
                System.out.println("\n" + course);
            }
            sub.setExerciseMax(course.getExercises()[sub.getWeek()]);
            System.out.println(" " + sub);
            
            hours += sub.getHours();
            exercises += sub.getExercises().length;
        }
        System.out.println("yhteensä: " + exercises + "/" + course.getExercisesSum() 
                + " tehtävää, " + hours + " tuntia");
        System.out.println(course.stats() + "\n");
    }
    
    public static String request(String url) throws IOException {
        return Request.Get(url).execute().returnContent().asString();
    }
}