package Week3.Bonus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person implements Comparable<Person>, Node {
    private String name;
    private LocalDate birthDate;
    private Map<Node , String> connections;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.connections = new HashMap<>();
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }
    
    public Map<Node, String> getConnections() {
        return this.connections;
    }

    public List<Node> getNeighborns(){
        List<Node> neighborns = new ArrayList<>();
        for(Node key : this.connections.keySet())
        {
            neighborns.add(key);
        }
        return neighborns;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addConnection(Node node) {
        boolean flag = true;
        for(Node key : connections.keySet())
            if(key.equals(node))
                flag = false;
        if(flag)
        {
            if(node instanceof Person){
                connections.put(node, "collegues");
                ((Person) node).connections.put(this, "collegues");
            }
            if(node instanceof Company){
                connections.put(node, "employer");
                ((Company) node).addConnection(this);
            }
        }
    }

    public int getImportance()
    {
        return this.connections.size();
    }

    public int compareTo(Person other) {
        return Integer.compare(other.getImportance(), this.getImportance());
    }
}
