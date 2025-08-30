package src.domain.student.cache;

import src.domain.student.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StudentCache {
    private final Map<String, Student> studentMap = new HashMap<>();

    public void addStudent(String studentName, Student student) {
        studentMap.put(studentName, student);
    }

    public Student getStudentByName(String studentName) {
        return studentMap.get(studentName);
    }

    public Collection<Student> getAllStudents() {
        return studentMap.values();
    }
}
