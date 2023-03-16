package Week3.Bonus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company implements Node {
    private String name;
    private Map<Node, String> connections;

    public Company(String name) {
        this.name = name;
        this.connections = new HashMap<>();
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
        if(flag) {
            if(node instanceof Company){
                connections.put(node, "partnership");
                ((Company) node).connections.put(this, "partnership");
            }
            if(node instanceof Person){
                connections.put(node, "employee");
                ((Person) node).addConnection(this);
            }
        }
    }

    public int getImportance()
    {
        return this.connections.size();
    }

    public int compareTo(Company other) {
        return Integer.compare(other.getImportance(), this.getImportance());
    }

}
