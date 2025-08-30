package src.domain;

import src.domain.student.Student;
import src.domain.student.cache.StudentCache;
import src.domain.student.service.StudentService;

import java.util.Collection;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final StudentService studentService = new StudentService(new StudentCache());

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int option = readUserOption();

            switch (option) {
                case 1 -> handleRegisterStudent();
                case 2 -> handleAddSubjectToStudent();
                case 3 -> handleAddGrade();
                case 4 -> handleReports();
                case 5 -> handleClassSummary();
                case 0 -> {
                    System.out.println("Desligando o programa...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Sistema Gerenciador de Notas ---");
        System.out.println("1. Cadastrar Novo Aluno");
        System.out.println("2. Adicionar Matéria para um Aluno");
        System.out.println("3. Adicionar Nota");
        System.out.println("4. Ver Relatório individual");
        System.out.println("5. Ver Relatório da turma");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static int readUserOption() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void handleRegisterStudent() {
        System.out.print("Digite o nome do novo aluno: ");
        String studentName = sc.nextLine();
        studentService.registerStudent(studentName);
    }

    private static void handleAddSubjectToStudent() {
        System.out.print("Digite o nome do aluno: ");
        String studentName = sc.nextLine();
        System.out.print("Digite o nome da matéria: ");
        String subjectName = sc.nextLine();
        studentService.registerStudentOnSubject(studentName, subjectName);
    }

    private static void handleAddGrade() {
        System.out.println("Digite o nome do aluno: ");
        String studentName = sc.nextLine();
        System.out.println("Digite o nome da matéria: ");
        String subjectName = sc.nextLine();
        System.out.println("Digite a nota a ser adicionada: ");
        double grade;
        try {
            grade = Double.parseDouble(sc.nextLine().replace(',', '.'));
        } catch (NumberFormatException e) {
            System.out.println("Nota inválida. Por favor, digite um número correto.");
            return;
        }
        studentService.addGradeToStudent(studentName, subjectName, grade);
    }

    private static void handleReports() {
        System.out.println("--- Geração de Relatório ---");
        System.out.print("Digite o nome do aluno para ver o relatório: ");
        String studentName = sc.nextLine();
        Student student = studentService.getStudentByName(studentName);
        if (student == null) {
            System.out.println("Aluno '" + capitalize(studentName) + "' não encontrado!");
            return;
        }
        displaySummary(student);
    }

    private static void displaySummary(Student student) {
        System.out.println("-----------------------------------------");
        System.out.println("Relatório do Aluno: " + capitalize(student.getName()));
        System.out.println("-----------------------------------------");
        System.out.println("Resumo Geral: ");
        double overallAverage = student.calculateOverallAverage();
        System.out.printf("    - Média Geral: %.2f%n", overallAverage);
        boolean isPassing = student.isPassing();
        System.out.println("    - Situação: " + (isPassing ? "Aprovado" : "Reprovado"));
        System.out.println("-----------------------------------------");
        System.out.println(" Detalhes por Disciplina:");
        if (student.getSubjects().isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada");
            return;
        }
        for (String subjectName : student.getSubjects().keySet()) {
            System.out.println("    " + capitalize(subjectName));
            double subjectAverage = student.calculateSubjectAverage(subjectName);
            Double highestForSubject = student.findHighestGradeForSubject(subjectName);
            Double lowestForSubject = student.findLowestGradeForSubject(subjectName);
            System.out.printf("    - Média: %.2f%n", subjectAverage);
            if (highestForSubject != null) {
                System.out.printf("    - Maior Nota: %.2f%n", highestForSubject);
            }
            if (lowestForSubject != null) {
                System.out.printf("    - Menor Nota: %.2f%n", lowestForSubject);
            }
        }
        System.out.print("-----------------------------------------");
    }

    private static void handleClassSummary() {
        System.out.println("--------- Relatório Geral da Turma  ---------");
        Collection<Student> allStudents = studentService.getAllStudents();
        if (allStudents.isEmpty()) {
            System.out.println("Não há alunos cadastrados para exibir.");
            System.out.println("-------------------------------------------------");
            return;
        }
        for (Student student : allStudents) {
            displaySummary(student);
        }
        System.out.println("\n--- Fim do Relatório Geral ---");
    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String firstLetter = text.substring(0, 1).toUpperCase();
        String restOfText = text.substring(1).toLowerCase();
        return firstLetter + restOfText;
    }

}