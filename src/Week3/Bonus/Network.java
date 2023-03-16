package Week3.Bonus;

import java.util.*;

public class Network {
    private List<Node> nodes;
    private Map<Node,Node> connection = new HashMap<>();

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
    public List<Node> getNeighbors(Node currentNode) {
        List<Node> neighbors = new ArrayList<>();
        for (Node node : nodes) {
            if (connection.get(currentNode).equals(node)) {
                neighbors.add(node);
            }
        }
        return neighbors;
    }
        public void dfs(int currentNode, int[] disc, int[] low, int[] parent, boolean[] visited, boolean[] articulationPoint, int time){
            visited[currentNode] = true;
            low[currentNode]=disc[currentNode]=time;
            time++;
            int children = 0;
            for(Node node : nodes.get(currentNode).getNeighborns()){
                int index = nodes.indexOf(node);
                if (!visited[index]) {
                    children++;
                    parent[index] = currentNode;
                    dfs(index, disc, low, parent, visited, articulationPoint, time);

                    low[currentNode] = Math.min(low[currentNode], low[index]);

                    if (parent[currentNode] == -1 && children > 1) {
                        articulationPoint[currentNode] = true;
                    }
                    if (parent[currentNode] != -1 && low[index] >= disc[currentNode]) {
                        articulationPoint[currentNode] = true;
                    }
                } else if (index != parent[currentNode]) {
                    low[currentNode] = Math.min(low[currentNode], disc[index]);
                }
            }
        }
    public List<Node> getArticulationPoints(){
        int disc[] = new int[nodes.size()];
        int low[] = new int[nodes.size()];
        int parent[] = new int[nodes.size()];
        Arrays.fill(parent,-1);
        boolean visited[] = new boolean[nodes.size()];
        Arrays.fill(visited,false);
        boolean articulationPoint[] = new boolean[nodes.size()];
        int time = 0;
        for(int i = 0;i < nodes.size(); i++)
        {
            if(!visited[i])
                dfs(i,disc,low,parent,visited,articulationPoint,time);
        }

        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            if (articulationPoint[i]) {
                result.add(nodes.get(i));
            }
        }

        return result;
    }

    public void dfsBlocks(int currentNode, int[] disc, int[] low, int[] parent, boolean[] visited, List<List<Node>> blocks, int time){
        visited[currentNode] = true;
        low[currentNode]=disc[currentNode]=time;
        time++;
        List<Node> children = new ArrayList<Node>();
        for(Node node : nodes.get(currentNode).getNeighborns()){
            int index = nodes.indexOf(node);
            if (!visited[index]) {
                children.add(node);
                parent[index] = currentNode;
                dfsBlocks(index, disc, low, parent, visited, blocks, time);

                low[currentNode] = Math.min(low[currentNode], low[index]);

                if (low[index] >= disc[currentNode]) {
                    List<Node> block = new ArrayList<Node>();
                    block.add(nodes.get(currentNode));
                    block.add(node);
                    while(!children.isEmpty() && !children.get(children.size()-1).equals(node))
                        block.add(children.remove(children.size()-1));
                    if(!blocks.contains(block))
                        blocks.add(block);
                }
            } else if (index != parent[currentNode]) {
                low[currentNode] = Math.min(low[currentNode], disc[index]);
                if(disc[index] < disc[currentNode])
                    children.add(node);
            }
        }
    }
    public List<List<Node>> getBlocks(){
        int disc[] = new int[nodes.size()];
        int low[] = new int[nodes.size()];
        int parent[] = new int[nodes.size()];
        Arrays.fill(parent,-1);
        boolean visited[] = new boolean[nodes.size()];
        Arrays.fill(visited,false);
        List<List<Node>> blocks = new ArrayList<>();
        int time = 0;
        for(int i = 0;i < nodes.size(); i++)
        {
            if(!visited[i])
                dfsBlocks(i,disc,low,parent,visited,blocks,time);
        }

        return blocks;
    }


    public void prinNetwork()
    {
        this.computeImportance();
        for(Node node : nodes)
        {
            System.out.println(node.getName() + ", importance = " + node.getImportance() + ";");
            Map<Node, String> connections = new HashMap<>();
            if(node instanceof Person)
                connections = ((Person) node).getConnections();
            if(node instanceof Company)
                connections = ((Company) node).getConnections();
            for(Node relatedNode : connections.keySet())
            {
                String connection = connections.get(relatedNode);
                System.out.println(relatedNode.getName() + " " + connection);
            }
            System.out.println();
        }
    }

    public void printArticulationNodes()
    {
        for(Node node : getArticulationPoints())
        {
            System.out.println(node.getName());
        }
    }

    public void printBlocks()
    {
        System.out.println();
        for(List<Node> nodeRow : getBlocks())
        {
            for(Node node : nodeRow)
                System.out.println(node.getName() + " " + getBlocks().get(0).get(1).getName());
            System.out.println();
        }
    }
}
