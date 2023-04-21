package org.example;

import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App 
{
    public static void main(String[] args) {
        LinkedList<Student> students = Stream.of("Bob", "Alice", "Alin")
                .map(Student::new)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));

        // print sorted students
        System.out.println("Sorted students:");
        students.forEach(System.out::println);

        TreeSet<Project> projects = Stream.of("Project B", "Project A", "Project C")
                .map(Project::new)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Sorted projects:");
        projects.forEach(System.out::println);
    }
}
