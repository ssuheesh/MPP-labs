import prob2A.Student;

public class Main2A {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Student s1 = new Student("Sukhbat", "4.0");
        Student s2 = new Student("Bob", "3.2");
        Student s3 = new Student("John", "2.9");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(s1.getGradeReport());
        System.out.println(s2.getGradeReport());
        System.out.println(s3.getGradeReport());

    }
}