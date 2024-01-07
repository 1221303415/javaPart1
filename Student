import java.util.ArrayList;

public class Student extends User{
    private ArrayList<String> courses;

    public Student(int id,String name){
        super(id,name,"Student");
        this.courses = new ArrayList<>();
    }

    public ArrayList<String> getCourses() {
        return courses;
    }
    public void addCourse(String course) {
        courses.add(course);
    }
    public void dropCourse(String course) {
        courses.remove(course);
    }
    public String toString() {
        return "ID: " + getId() + "\tName: " + getPassword() + "\nCourse: " + courses;
    }
    
    // Registers a student for a course if the course code is valid and shows an error message if invalid course code
    public void registerCourse(String courseCode) {
        if (isValidCourseCode(courseCode)) {
            courses.add(courseCode);
            System.out.println("Registered for course: " + courseCode);
        } else {
            System.out.println("Error: Invalid course code. Please enter a valid course code.");
        }
    }

    // Checks if a course code is in a valid format
    private boolean isValidCourseCode(String courseCode) {
        return courseCode.matches("CS\\d{3}");      // "\d{3}" means that there are exactly three digits following "CS". 
    }
}
