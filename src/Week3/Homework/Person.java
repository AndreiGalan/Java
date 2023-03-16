package Week3.Homework;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Person implements Comparable<Person>, Node {
    private String name;
    private LocalDate birthDate;
    private Map<String ,Node> connections;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.connections = new HashMap<>();
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }


    public Map<String, Node> getConnections() {
        return this.connections;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addConnection(String key, Node node) {
        this.connections.put(key,node);
    }

    public int getImportance()
    {
        return this.connections.size();
    }

    public int compareTo(Person other) {
        return Integer.compare(other.getImportance(), this.getImportance());
    }
}
