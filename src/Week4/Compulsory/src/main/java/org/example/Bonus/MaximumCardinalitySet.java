package org.example.Bonus;

import org.jgrapht.Graph;
import org.jgrapht.alg.matching.HopcroftKarpMaximumCardinalityBipartiteMatching;
import org.jgrapht.graph.AsSubgraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import com.github.javafaker.Faker;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class MaximumCardinalitySet {
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

        Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        Faker faker = new Faker();
        List<Student> students = Stream.generate(() -> new Student(faker.name().fullName()))
                .limit(10)
                .collect(Collectors.toList());
        List<Project> projects = Stream.generate(() -> new Project(faker.company().name()))
                .limit(20)
                .collect(Collectors.toList());


        Random random = new Random();
        students.forEach(student -> {
            List<Project> preferences = new ArrayList<>(projects);
            Collections.shuffle(preferences);
            student.setPreferences(preferences.subList(0, random.nextInt(4) + 1));
        });

        makeGraph(graph, students);
        Set<String> studentSet = new HashSet<>(graph.vertexSet());
        Set<String> projectSet = new HashSet<>(graph.vertexSet());
        projectSet.removeAll(studentSet);
        HopcroftKarpMaximumCardinalityBipartiteMatching<String, DefaultEdge> matching = new HopcroftKarpMaximumCardinalityBipartiteMatching<>(graph, studentSet, projectSet);
        Set<DefaultEdge> edges = matching.getMatching().getEdges();

        Set<String> vertexSet = new HashSet<>();
        for (DefaultEdge edge : edges) {
            vertexSet.add(graph.getEdgeSource(edge));
            vertexSet.add(graph.getEdgeTarget(edge));
        }

        Set<String> remainingSet = new HashSet<>(graph.vertexSet());
        remainingSet.removeAll(vertexSet);

        System.out.println("Maximum cardinality set:");
        for (String vertex : remainingSet) {
            System.out.println(vertex);
        }

    }
}
