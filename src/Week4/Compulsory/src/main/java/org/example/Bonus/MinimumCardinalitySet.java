package org.example.Bonus;

import org.jgrapht.Graph;
import org.jgrapht.alg.matching.EdmondsMaximumCardinalityMatching;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinimumCardinalitySet  {
    static void makeGraph(Graph<String, DefaultEdge> graph, List<Student> students)
    {
        students.stream()
                .forEach(student -> {
                    graph.addVertex(student.getName());
                    for(Project project : student.getPreferences()){
                        graph.addVertex(project.getName());
                        graph.addEdge(student.getName(),project.getName());
                    }
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


        Random random = new Random();
        students.forEach(student -> {
            List<Project> preferences = new ArrayList<>(projects);
            Collections.shuffle(preferences);
            student.setPreferences(preferences.subList(0, random.nextInt(4) + 1));
        });

        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        makeGraph(graph, students);
        EdmondsMaximumCardinalityMatching<String, DefaultEdge> matching = new EdmondsMaximumCardinalityMatching<>(graph);
        matching.getMatching().forEach(System.out::println);
    }
}
