package org.example.Homework;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javafaker.Faker;

public class App {
    static void putPreferences(List<Student> students, List<Project> projects)
    {
        Random random = new Random();
        students.forEach(student -> {
            List<Project> preferences = new ArrayList<>(projects);
            Collections.shuffle(preferences);
            student.setPreferences(preferences.subList(0, random.nextInt(4) + 1));
        });
    }

    static double getAvg(List<Student> students)
    {
        double avgPrefs = students.stream()
                .mapToDouble(student -> student.getPreferences().size())
                .average()
                .orElse(0.0);
        return avgPrefs;
    }

    static void assignProjects(List<Student> students, List<Project> projects)
    {
        List<Project> assignedProjects = new ArrayList<>();
        students.stream()
                .sorted()
                .forEach(student -> {
                    for (Project preference : student.getPreferences()) {
                        if (!assignedProjects.contains(preference)) {
                            student.setAssignedProject(preference);
                            break;
                        }
                    }
                    if(student.getAssignedProject() ==  null)
                        for(Project project : projects)
                            if (!assignedProjects.contains(project)) {
                                student.setAssignedProject(project);
                                break;
                            }
                    assignedProjects.add(student.getAssignedProject());
                });
    }
    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Student> students = Stream.generate(() -> new Student(faker.name().fullName()))
                .limit(10)
                .collect(Collectors.toList());
        List<Project> projects = Stream.generate(() -> new Project(faker.company().name()))
                .limit(10)
                .collect(Collectors.toList());

        putPreferences(students,projects);


        System.out.println("Students with preferences lower than average (" + getAvg(students) + "):");
        students.stream()
                .filter(student -> student.getPreferences().size() < getAvg(students))
                .forEach(System.out::println);

        assignProjects(students, projects);

        System.out.println("\nAssigned projects:");
        students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(student -> System.out.println(student.getName() + ": " + student.getAssignedProject().getName()));

    }
}