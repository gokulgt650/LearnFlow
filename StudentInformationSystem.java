import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private HashMap<String, String> grades;
    private ArrayList<String> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.grades = new HashMap<>();
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void registerCourse(String course) {
        registeredCourses.add(course);
    }

    public void assignGrade(String course, String grade) {
        grades.put(course, grade);
    }

    public void printTranscript() {
        System.out.println("Transcript for " + name + " (" + studentID + "):");
        for (String course : grades.keySet()) {
            System.out.println(course + ": " + grades.get(course));
        }
    }

    public void printRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (" + studentID + "):");
        for (String course : registeredCourses) {
            System.out.println(course);
        }
    }
}

public class StudentInformationSystem {
    private static ArrayList<Student> students = new ArrayList<>();

    public static Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to Student Information System");
            System.out.println("1. Register Student");
            System.out.println("2. Register Course");
            System.out.println("3. Assign Grade");
            System.out.println("4. Print Transcript");
            System.out.println("5. Print Registered Courses");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter student name: ");
                    String name = scanner.next();
                    students.add(new Student(studentID, name));
                    System.out.println("Student registered successfully!");
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    Student student = findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter course name: ");
                        String course = scanner.next();
                        student.registerCourse(course);
                        System.out.println("Course registered successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    student = findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter course name: ");
                        String course = scanner.next();
                        System.out.print("Enter grade: ");
                        String grade = scanner.next();
                        student.assignGrade(course, grade);
                        System.out.println("Grade assigned successfully!");
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    student = findStudent(studentID);
                    if (student != null) {
                        student.printTranscript();
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    student = findStudent(studentID);
                    if (student != null) {
                        student.printRegisteredCourses();
                    } else {
                        System.out.println("Student not found!");
                    }
                    break;
                case 6:
                    System.out.println("Exiting... Thank you for using Student Information System.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}