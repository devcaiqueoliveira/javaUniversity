package src.domain;

public class StudentManager {

    public float calculateAverage(Student student) {
        float media=0;
        float[] studentGrades = student.getGrades();
        for(float nota:studentGrades ){
            media += nota;
        }
        media /= studentGrades.length;
        return media;
    }

    public void displayStudentAverage(Student student) {
        System.out.println("\n Média de "+ student.getName() + " : "
                + calculateAverage(student));
    }
}
