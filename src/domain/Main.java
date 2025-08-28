package src.domain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();
        Student student = new Student("Alice", new float[]{100F, 100F, 100F});
        studentManager.displayStudentAverage(student);
        sc.close();
    }
}