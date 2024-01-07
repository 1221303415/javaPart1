import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    static ArrayList<Student> listStudents = new ArrayList<>();
    static ArrayList<Lecturer> listLecturers = new ArrayList<>();
    static User loggedInUser;

    public static void main(String[] args) {
       initializeData();

       Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Course Registration");
        char choice;
        do {
            System.out.println();
            dashboard();
            choice = input.next().charAt(0);
            switch (choice) {
                case '1':
                    createStudents(listStudents);
                    break;
        
                case '2':
                    createLecturers(listLecturers);
                    break;

                case '3' :
                    assignCourse( listLecturers);
                    break;
                
                case '4':
                    while (true) {
                        if (loggedInUser == null) {
                            login(input); // If no user is logged in, prompt for login
                        } else {
                            executeCommand(input); // If a user is logged in, execute commands
                        }
                    }

                case '5':
                    viewList(listStudents, listLecturers);
                    break;
        
                case '0':
                    System.out.println("Thank you.");
                    break;
        
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != '0');
    }

    public static void initializeData(){
        listStudents.add(new Student(1221,"Azraf"));
        listStudents.add(new Student(1223,"Adam"));
        listStudents.add(new Student(1225,"Bob"));
        listStudents.add(new Student(1227,"Bubu"));
        listStudents.add(new Student(1229,"Calli"));
        listStudents.add(new Student(1231,"Cathy"));
        listStudents.add(new Student(1233,"Daxton"));
        listStudents.add(new Student(1235,"Didi"));

        for (Student student : listStudents) {
            if(student.getId() <= 1225)
            student.addCourse("CS113");
            else if(student.getId() <= 1229)
            student.addCourse("CS123");
            else if(student.getId() <= 1231)
            student.addCourse("CS133");
            else if(student.getId() <= 1235)
            student.addCourse("CS143");
        }

        listLecturers.add(new Lecturer(2221, "NLK", "CS113"));
        listLecturers.add(new Lecturer(2223, "BLK", "CS123"));
        listLecturers.add(new Lecturer(2225, "ALK", "CS133"));
        listLecturers.add(new Lecturer(22273, "CLK", "CS143"));

    }

    public static void dashboard() {
        System.out.println("1) Create Students(Task 1)");
        System.out.println("2) Create Lecturers(Task 1)");
        System.out.println("3) Admin can create courses and assign courses courses to lecturers(Task 2)");
        System.out.println("4) Login system(Task 3)");
        System.out.println("4) Students self-register courses(Task 4)");
        System.out.println("4) Lecturers can view all the students in their courses(Task 5)");
        System.out.println("5) View Students/Lecturers(Task 6)");
        System.out.println("0)Exit");
    }
//--------------------------------------------------------------------------------------------------
    public static void assignCourse(ArrayList<Lecturer> listLecturers) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter new Course: ");
        String course = input.nextLine();
        System.out.println("Enter Lecturer's ID: ");
        int lecturerId = input.nextInt();

        for (Lecturer l : listLecturers) {
            if (l.getId() == lecturerId) {
                l.addCourse(course);
            }
        }
    }
//-----------------------------------------------------------------------------------------------------
    public static void createStudents(ArrayList<Student> listStudents) {
        Scanner input = new Scanner(System.in);
        int id;
        String name;

        do {
            System.out.println("Enter student details (enter a negative ID to exit):");
            System.out.print("ID: ");
            id = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (id >= 0) {
                System.out.print("Name: ");
                name = input.nextLine();

                Student student = new Student(id, name);

                // Adding Courses
                System.out.print("Add Course (enter 'done' to finish): ");
                String course;
                do {
                    course = input.next();
                    if (!course.equalsIgnoreCase("done")) {
                        student.addCourse(course);
                    }
                } while (!course.equalsIgnoreCase("done"));

                if (!listStudents.contains(student)) {
                    listStudents.add(student);
                } else {
                    System.out.println("Student with the same ID already exists. Please try again.");
                }
            }
        } while (id >= 0);
    }

    public static void createLecturers(ArrayList<Lecturer> listLecturers) {
        Scanner input = new Scanner(System.in);
        int id;
        String name;
        String course;

        do {
            System.out.println("Enter lecturer details (enter a negative ID to exit):");
            System.out.print("ID: ");
            id = input.nextInt();
            input.nextLine(); // Consume the newline character

            if (id >= 0) {
                System.out.print("Name: ");
                name = input.nextLine();
                input.nextLine();
                System.out.print("Course: ");
                course = input.nextLine();

                Lecturer lecturer = new Lecturer(id, name, course);
                if (!listLecturers.contains(lecturer)) {
                    listLecturers.add(lecturer);
                } else {
                    System.out.println("Lecturer with the same ID already exists. Please try again.");
                }
            }
        } while (id >= 0);
    }

    public static void viewList(ArrayList<Student> listStudents, ArrayList<Lecturer> listLecturers) {

        // To search Students and Lecturers in the Array
        Scanner input = new Scanner(System.in);
        String course;

        System.out.print("Enter course: ");
        course = input.next();

        System.out.println("Students registered for course " + ": " + course);
        for (Student student : listStudents) {
            ArrayList<String> courses = student.getCourses();
            if (courses.contains(course)) {
                System.out.println(student);
            }
        }

        System.out.println("Lecturers assigned to course " + ": " + course);
        for (Lecturer lecturer : listLecturers) {
            ArrayList<String> assignCourses = lecturer.getAssignCourses();
            if (assignCourses.contains(course)) {
                System.out.println(lecturer);
            }
        }
    }

    // Method to handle user login
    private static void login(Scanner input) {
        System.out.print("Enter id: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Enter password: ");
        String name = input.nextLine();

        loggedInUser = authenticateUser(id, name);

        if (loggedInUser != null) {
            System.out.println("\n\nLogin successful!");
        } else {
            System.out.println("Invalid id or password. Please try again.\n");
        }
    }

    // Method to authenticate user based on provided credentials
    private static User authenticateUser(int id, String name) {
        for (Student student : listStudents) {
            // Check if the provided id and password match a student's credentials
            if (student.getId() == id && student.getPassword().equals(name)) {
                return student;
            }
        }

        for (Lecturer lecturer : listLecturers) {
            // Check if the provided id and password match a lecturer's credentials
            if (lecturer.getId() == id && lecturer.getPassword().equals(name)) {
                return lecturer;
            }
        }

        return null;
    }

    // Method to handle user commands
    private static void executeCommand(Scanner input) {

        // Display user information and prompt for a command
        System.out.print("\nYou are a " + loggedInUser.getUserType() + " (" 
                        + loggedInUser.getId() + ")\nEnter command (type 'logout' to logout): "); 
        String command = input.nextLine();

         // Check if the user wants to log out
        if (command.equalsIgnoreCase("logout")) {
            loggedInUser = null;        // Set loggedInUser to null, indicating the user has logged out
            System.out.println("Logged out successfully.\n");
        } 

        // Check if the logged-in user is a Student
        else if (loggedInUser instanceof Student) {
            processStudentCommand(command);
        } 

        // Check if the logged-in user is a Lecturer
        else if (loggedInUser instanceof Lecturer) {
            processLecturerCommand(command);
        }
    }

    // Method to handle student-specific commands
    private static void processStudentCommand(String command) {

        // Checks if the command starts with "register"
        if (command.startsWith("register")) {

            // Extracts the course code part from the command after "register" and trim any extra spaces
            String courseCode = command.substring("register".length()).trim();
            Student student = (Student) loggedInUser;   // Gets the logged-in user as a Student

            // Registers the student for the specified course
            student.registerCourse(courseCode);
        } else {
            System.out.println("Invalid command for student.");
        }
    }

    // Method to handle lecturer-specific commands
    private static void processLecturerCommand(String command) {

        // Checks if the command starts with "view student"
        if (command.startsWith("view student")) {

            // Extracts the course code part from the command after "view student" and trim any extra spaces
            String courseCode = command.substring("view student".length()).trim(); // 
            Lecturer lecturer = (Lecturer) loggedInUser;

            // Checks if the lecturer is assigned to the specified course
            if (lecturer.getAssignCourses().contains(courseCode)) {
                System.out.println("Students in course " + courseCode + ":");

                for (Student student : listStudents) {  // Iterate through all students
                    // Checks if the student is registered for the specified course
                    if (student.getCourses().contains(courseCode)) {
                        System.out.println("- " + student.getId());
                    }
                }
            } else {
                System.out.println("You are not assigned to course " + courseCode);
            }
        } else {
            System.out.println("Invalid command for lecturer.");
        }
    }
}
