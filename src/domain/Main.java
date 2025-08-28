package src.domain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do aluno:");
        String name = sc.nextLine();
        System.out.println("Digite as notas das 3 matérias do aluno. Matemática, Física e Química:");
        int[] grades = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        String[] disciplinas = {"Matemática","Física","Química"};

        for(int i=0;i<grades.length;i++){
            System.out.print(disciplinas[i]+": " + grades[i]+", ");
        }
        sc.close();

        float media=0;
        for(int nota: grades){
            media += nota;
        }
        media /= grades.length;
        System.out.println("\n Média: " + media);

        if(media>=60){
            System.out.println("Aprovado");
        }else{
            System.out.println("Reprovado");
        }

        int max=grades[0], min =grades[0];
        for(int nota: grades){
            if(nota>max){
                max = nota;
            }
            if(nota<min){
                min=nota;
            }
        }

        System.out.println("Alta: "+max);
        System.out.println("Baixa: "+min);
        sc.close();
    }
}