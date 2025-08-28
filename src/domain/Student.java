package src.domain;

public class Student {
    private String name;
    private float[] grades;

    public Student(String name, float[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getGrades() {
        return grades;
    }

    public void setGrades(float[] grades) {
        this.grades = grades;
    }
}
