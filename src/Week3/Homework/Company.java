package Week3.Homework;


import java.util.HashMap;
import java.util.Map;

public class Company implements Node {
    private String name;
    private Map<String ,Node> connections;

    public Company(String name) {
        this.name = name;
        this.connections = new HashMap<>();
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

    public int compareTo(Company other) {
        return Integer.compare(other.getImportance(), this.getImportance());
    }

}
