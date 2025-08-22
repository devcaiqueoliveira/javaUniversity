package src.domain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do aluno:");
        String name = sc.nextLine();
        System.out.println("Digite as notas das 3 matérias do aluno. Matemática, Física e Química:");
        int[] grades = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        System.out.println("Matemática: " + grades[0] + " Física: " + grades[1] + " Química: " + grades[2]);

        sc.close();
    }
}