package src.domain.student;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Student {
    private final String name;
    private final Map<String, List<Double>> subjects;

    public Student(String name) {
        this.name = name;
        this.subjects = new HashMap<>();
    }

    public double calculateSubjectAverage(String subjectName) {
        List<Double> grades = subjects.get(subjectName.toLowerCase());
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;

        for (Double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    public double calculateOverallAverage() {
        double totalSum = 0.0;
        int totalCount = 0;
        for (List<Double> gradesOfOneSubject : subjects.values()) {
            for (Double grade : gradesOfOneSubject) {
                totalSum += grade;
                totalCount++;
            }
        }
        if (totalCount == 0) {
            return 0.0;
        }
        return totalSum / totalCount;
    }

    public Double findHighestGradeForSubject(String subjectName) {
        List<Double> grades = subjects.get(subjectName.toLowerCase());
        if (grades == null || grades.isEmpty()) {
            return null;
        }

        double highestGrade = -1.0;
        for (Double grade : grades) {
            if (grade > highestGrade) {
                highestGrade = grade;
            }
        }
        return highestGrade;
    }

    public Double findLowestGradeForSubject(String subjectName) {
        List<Double> grades = subjects.get(subjectName.toLowerCase());
        if (grades == null || grades.isEmpty()) {
            return null;
        }
        double lowestGrade = 101.0;
        for (Double grade : grades) {
            if (grade < lowestGrade) {
                lowestGrade = grade;
            }
        }
        return lowestGrade;
    }

    public boolean isPassing() {
        return calculateOverallAverage() >= 60.0;
    }
}