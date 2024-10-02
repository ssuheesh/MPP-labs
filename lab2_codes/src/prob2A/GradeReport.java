package prob2A;

public class GradeReport {
    private String grade;
    private Student owner;
    private GradeReport(String grade, Student student) {
        this.grade = grade;
        this.owner = student;
    }

    public static GradeReport createGradeReport(String grade, Student student) {
        if(student == null)
            throw new NullPointerException("Student is null");
        return new GradeReport(grade,student);
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Student getOwner() {
        return owner;
    }
    @Override
    public String toString() {
        return "Grade: "+ grade;
    }
}
