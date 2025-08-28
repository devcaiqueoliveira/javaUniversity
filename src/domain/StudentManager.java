package src.domain;

public class StudentManager {

    public float calculateAverage(Student student) {
        float media = 0;
        float[] studentGrades = student.getGrades();
        for (float nota : studentGrades) {
            media += nota;
        }
        media /= studentGrades.length;
        return media;
    }

    public void displayStudentAverage(Student student) {
        System.out.println("\n Média de " + student.getName() + " : " + calculateAverage(student));
    }

    public boolean isPassing(Student student) {
        float media = calculateAverage(student);
        return media >= 60;
    }

    public float findHighestGrade(Student student) {
        float[] grades = student.getGrades();
        float max = grades[0];
        for(float grade: grades){
            if (grade > max) {
                max = grade;
            }
        }
        return max;
    }

    public float findLowestGrade(Student student){
        float[] grades = student.getGrades();
        float min = grades[0];
        for(float grade: grades){
            if (grade < min) {
                min = grade;
            }
        }
        return min;
    }
}
