import java.io.*;
import java.util.*;

public class StudentManager {
    private static final String FILE_NAME = "students.ser";
    private static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchStudent(sc);
                case 4 -> updateStudent(sc);
                case 5 -> deleteStudent(sc);
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        sc.close();
        saveData();
    }

    private static void addStudent(Scanner sc) {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = sc.nextInt();
            sc.nextLine();

            for (Student s : studentList) {
                if (s.getRollNumber() == roll) {
                    System.out.println("Roll number already exists!");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Guardian Name: ");
            String guardian = sc.nextLine();

            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            studentList.add(new Student(roll, name, age, course, gender, guardian, address));
            System.out.println("Student added successfully.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct details.");
            sc.nextLine();
        }
    }

    private static void viewStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("Total Students: " + studentList.size());
        studentList.sort(Comparator.comparingInt(Student::getRollNumber));

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    private static void searchStudent(Scanner sc) {
        System.out.println("Search by: 1. Roll Number  2. Name");
        try {
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> {
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    for (Student s : studentList) {
                        if (s.getRollNumber() == roll) {
                            System.out.println(s);
                            return;
                        }
                    }
                    System.out.println("Student not found.");
                }
                case 2 -> {
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().toLowerCase();
                    boolean found = false;

                    for (Student s : studentList) {
                        if (s.getName().toLowerCase().contains(name)) {
                            System.out.println(s);
                            found = true;
                        }
                    }

                    if (!found) System.out.println("Student not found.");
                }
                default -> System.out.println("Invalid option.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    private static void updateStudent(Scanner sc) {
        System.out.print("Enter Roll Number to update: ");
        try {
            int roll = sc.nextInt();
            sc.nextLine();

            for (Student s : studentList) {
                if (s.getRollNumber() == roll) {
                    System.out.print("Enter new name: ");
                    s.setName(sc.nextLine());

                    System.out.print("Enter new age: ");
                    s.setAge(sc.nextInt());
                    sc.nextLine();

                    System.out.print("Enter new course: ");
                    s.setCourse(sc.nextLine());

                    System.out.print("Enter new gender: ");
                    s.setGender(sc.nextLine());

                    System.out.print("Enter new guardian name: ");
                    s.setGuardianName(sc.nextLine());

                    System.out.print("Enter new address: ");
                    s.setAddress(sc.nextLine());

                    System.out.println("Student updated successfully.");
                    return;
                }
            }
            System.out.println("Student not found.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Roll Number to delete: ");
        try {
            int roll = sc.nextInt();
            sc.nextLine();

            Iterator<Student> it = studentList.iterator();
            while (it.hasNext()) {
                if (it.next().getRollNumber() == roll) {
                    it.remove();
                    System.out.println("Student deleted successfully.");
                    return;
                }
            }
            System.out.println("Student not found.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            sc.nextLine();
        }
    }

    private static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            studentList = (ArrayList<Student>) ois.readObject();
        } catch (Exception e) {
            studentList = new ArrayList<>();
        }
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(studentList);
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }
}
