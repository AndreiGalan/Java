package Week3.Compulsory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Person("Andrei"));
        nodes.add(new Company("Amazon"));
        nodes.add(new Person("Ion"));
        nodes.add(new Company("Microsoft"));
        nodes.add(new Company("Google"));
        nodes.add(new Person("Claudiu"));

        Collections.sort(nodes, Comparator.comparing(Node::getName));

        for (Node node : nodes) {
            System.out.println(node.getName());
        }
    }
}
