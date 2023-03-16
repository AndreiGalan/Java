package Week3.Homework;

import java.util.*;

public class Network {
    private List<Node> nodes;

    public Network()
    {
        this.nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public void addNode(Node node)
    {
        this.nodes.add(node);
    }

    public void computeImportance() {
        Collections.sort(nodes, Comparator.comparing(Node::getImportance).reversed());
    }

    public void prinNetwork()
    {
        this.computeImportance();
        for(Node node : nodes)
        {
            System.out.println(node.getName() + ", importance = " + node.getImportance() + ";");
            Map<String,Node> connections = new HashMap<>();
            if(node instanceof Person)
                connections = ((Person) node).getConnections();
            if(node instanceof Company)
                connections = ((Company) node).getConnections();
            for(String connection : connections.keySet())
            {
                Node relatedNode = connections.get(connection);
                System.out.println(connection + " " + relatedNode.getName());
            }
            System.out.println();
        }
    }
}
