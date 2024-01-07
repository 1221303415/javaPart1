import java.util.ArrayList;
import java.util.Arrays;

public class Lecturer extends User {
    private ArrayList<String> assignCourses;

    public Lecturer(int id, String name, String assignCourse) {
        super(id, name, "Lecturer");
        this.assignCourses = new ArrayList<>(Arrays.asList(assignCourse));
    }

    public void addCourse(String course) {
        assignCourses.add(course);
    }

    public ArrayList<String> getAssignCourses() {
        return assignCourses;
    }

    public String toString(){
        return "Lecturer: " + getPassword();
    }
}
