import java.io.Serializable;

public class Student implements Serializable {
    private int rollNumber;
    private String name;
    private int age;
    private String course;
    private String gender;
    private String guardianName;
    private String address;

    public Student(int rollNumber, String name, int age, String course, String gender, String guardianName, String address) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.course = course;
        this.gender = gender;
        this.guardianName = guardianName;
        this.address = address;
    }

    public int getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }
    public String getGender() { return gender; }
    public String getGuardianName() { return guardianName; }
    public String getAddress() { return address; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }
    public void setGender(String gender) { this.gender = gender; }
    public void setGuardianName(String guardianName) { this.guardianName = guardianName; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "\nRoll Number : " + rollNumber +
                "\nName        : " + name +
                "\nAge         : " + age +
                "\nCourse      : " + course +
                "\nGender      : " + gender +
                "\nGuardian    : " + guardianName +
                "\nAddress     : " + address + "\n";
    }
}
