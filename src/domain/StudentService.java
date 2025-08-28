package src.domain;

public class StudentService {

    public float calculateAverage(Student student) {
        float media = 0;
        float[] studentGrades = student.getGrades();
        for (float nota : studentGrades) {
            media += nota;
        }
        media /= studentGrades.length;
        return media;
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

    public void displaySummary(Student student) {
        boolean isPassing = isPassing(student);
        String status = isPassing ? "Aprovado" : "Reprovado";
        System.out.println("Nome do Aluno: " + student.getName());
        System.out.printf("Media: " + String.format("%.1f%n", calculateAverage(student)));
        System.out.println("Status de Aprovação: " + status);
        System.out.println("Nota mais alta: " + findHighestGrade(student));
        System.out.println("Nota mais baixa: " + findLowestGrade(student));
    }
}