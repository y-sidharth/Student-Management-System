import java.util.*;

public class StudentManagementSystem {
    // We can store only up to 8 students
    static final int MAX_STUDENTS = 8;

    // List to store student details (each student is a Map like a Python dict)
    static ArrayList<Map<String, Object>> students = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Main program loop (keeps running until Exit is chosen)
        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your OPTION: ");

            int choice = readInt();
            sc.nextLine();
            switch (choice) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: deleteStudent(); break;
                case 6:
                    System.out.println("Thank you! Bye..Byee");
                    return;
                default:
                    System.out.println("No option, please try again and choose a correct option.");
            }
        }
    }
    // Helper to read(keeps asking until correct input)
    static int readInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Please enter a valid number: ");
                sc.nextLine();
            }
        }
    }
    // Add a student
    static void addStudent() {
        if (students.size() >= MAX_STUDENTS) {
            System.out.println("Cannot add more students (Limited admission Only).");
            return;
        }

        System.out.print("Enter ID: ");
        int id = readInt();
        sc.nextLine(); 
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course (CSE/ECE/IT/AIML/TIMPS): ");
        String course = sc.nextLine().trim().toUpperCase();
        // validate course
        if (!(course.equals("CSE") || course.equals("ECE") || course.equals("IT")
              || course.equals("AIML") || course.equals("TIMPS"))) {
            System.out.println("❌ Invalid course! Choose from: CSE, ECE, IT, AIML, TIMPS.");
            return;
        }
        System.out.print("Enter Marks: ");
        int marks = readInt();
        sc.nextLine();
        Map<String, Object> student = new HashMap<>(); // store details
        student.put("id ", id);
        student.put("name ", name);
        student.put("course ", course);
        student.put("marks ", marks);

        students.add(student);
        System.out.println("Student added successfully!");
    }
    // View all students
    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\nID\tName\t\tCourse\tMarks");
        for (Map<String, Object> st : students) {
            System.out.printf("%s\t%-12s\t%s\t%s\n",
                st.get("id"), st.get("name"), st.get("course"), st.get("marks"));
        }
    }
    // Search student by ID or Name
    static void searchStudent() {
        System.out.print("Enter Student ID or Name to search: ");
        String key = sc.nextLine().trim();

        boolean found = false;
        for (Map<String, Object> st : students) {
            if (String.valueOf(st.get("id")).equals(key) ||
                st.get("name").toString().equalsIgnoreCase(key)) {
                System.out.println(" Found: " + st);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    // Update student details (course or marks)
    static void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = readInt();
        sc.nextLine();
        for (Map<String, Object> st : students) {
            if ((int) st.get("id") == id) {
                System.out.print("What to update? (C for Coursu / M for Markulu): ");
                String choice = sc.nextLine().trim().toUpperCase();
                if (choice.equals("C")) {
                    System.out.print("Enter new Course (CSE/ECE/IT/AIML/TIMPS): ");
                    String newCourse = sc.nextLine().trim().toUpperCase();
                    if (!(newCourse.equals("CSE") || newCourse.equals("ECE") || newCourse.equals("IT")
                          || newCourse.equals("AIML") || newCourse.equals("TIMPS"))) {
                        System.out.println("Ee..course ledhu. Update cancelled.");
                        return;
                    }
                    st.put("course", newCourse);
                } else if (choice.equals("M")) {
                    System.out.print("Enter new Marks: ");
                    int newMarks = readInt();
                    sc.nextLine();
                    st.put("marks", newMarks);
                } else {
                    System.out.println("No option is There. Update cancelled.");
                    return;
                }
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }
    // Delete a student by ID
    static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = readInt();
        sc.nextLine();
        Iterator<Map<String, Object>> it = students.iterator();
        while (it.hasNext()) {
            Map<String, Object> st = it.next();
            if ((int) st.get("id") == id) {
                it.remove();
                System.out.println("papam.. Student removed.");
                return;
            }
        }
        System.out.println("Student not found.");
    }
}