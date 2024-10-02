package prob2A;

public class Student {
    private String name;
    private GradeReport gradeReport;

    public Student(String name, String grade) {
        this.name = name;
        this.gradeReport = GradeReport.createGradeReport(grade, this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GradeReport getGradeReport() {
        return gradeReport;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", gradeReport=" + gradeReport + "]";
    }
}
