package src.domain.student.service;

import lombok.RequiredArgsConstructor;
import src.domain.student.Student;
import src.domain.student.cache.StudentCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class StudentService {
    private final StudentCache studentCache;

    public Student getStudentByName(String studentName) {
        if (studentName == null) return null;
        return studentCache.getStudentByName(studentName.toLowerCase());
    }

    public Collection<Student> getAllStudents() {
        return studentCache.getAllStudents();
    }

    public void addGradeToStudent(String studentName, String subjectName, double grade) {
        Student student = this.getStudentByName(studentName);
        if (student == null) {
            System.out.println("Aluno " + studentName + " não encontrado.");
            return;
        }
        String formattedSubject = subjectName.toLowerCase();
        Map<String, List<Double>> subjectsOfStudent = student.getSubjects();
        if (!subjectsOfStudent.containsKey(formattedSubject)) {
            System.out.println("O aluno " + studentName + " não está matriculado nesta matéria.");
            System.out.println("Por favor, adicione o aluno a matéria usando a opção (2).");
            return;
        }
        List<Double> grades = subjectsOfStudent.get(formattedSubject);
        grades.add(grade);
        System.out.println("Nota adicionada com sucesso para o aluno " + studentName + " na matéria " + subjectName);
    }

    public void registerStudent(String studentName) {
        String formattedName = studentName.toLowerCase();
        if (studentCache.getStudentByName(studentName) != null) {
            System.out.println("Já existe um aluno com o nome " + studentName);
            return;
        }
        Student newStudent = new Student(studentName);
        studentCache.addStudent(formattedName, newStudent);
        System.out.println("Aluno " + studentName + " cadastrado.");
    }

    public void registerStudentOnSubject(String studentName, String subjectName) {
        Student student = getStudentByName(studentName);
        if (student == null) {
            System.out.println("Este aluno não existe no sistema.");
            return;
        }

        String formattedSubjectName = subjectName.toLowerCase();
        Map<String, List<Double>> subjectsOfStudents = student.getSubjects();
        if (subjectsOfStudents.containsKey(formattedSubjectName)) {
            System.out.println("O aluno " + studentName + " já está matriculado nesta matéria");
            return;
        }
        subjectsOfStudents.put(formattedSubjectName, new ArrayList<>());
        System.out.println("Aluno " + studentName + " adicionado a matéria " + subjectName);
    }
}
