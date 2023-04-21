package com.example.week6.Homework;


public class Strategy {
    private String name;

    public Strategy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean winCondition(Player player) {
        if(player.getEdges().size() < 3)
            return false;
        Edge startEdge = player.getEdges().get(player.getEdges().size() - 1);
        for(Edge edge : player.getEdges())
        {
            if(!edge.equals(startEdge)){
                if(startEdge.getNode1().equals(edge.getNode1())){
                    for(Edge edge2 : player.getEdges()){
                        if(!edge2.equals(edge) && !edge2.equals(startEdge))
                            if((edge2.getNode1().equals(edge.getNode2()) && edge2.getNode2().equals(startEdge.getNode2()))
                                    || (edge2.getNode2().equals(edge.getNode2()) && edge2.getNode1().equals(startEdge.getNode2())))
                                return true;
                    }
                }
                else
                if(startEdge.getNode1().equals(edge.getNode2())){
                    for(Edge edge2 : player.getEdges()){
                        if(!edge2.equals(edge) && !edge2.equals(startEdge))
                            if((edge2.getNode1().equals(edge.getNode1()) && edge2.getNode2().equals(startEdge.getNode2()))
                                    || (edge2.getNode2().equals(edge.getNode1()) && edge2.getNode1().equals(startEdge.getNode2())))
                                return true;
                    }
                }
                else
                if(startEdge.getNode2().equals(edge.getNode1())){
                    for(Edge edge2 : player.getEdges()){
                        if(!edge2.equals(edge) && !edge2.equals(startEdge))
                            if((edge2.getNode1().equals(edge.getNode2()) && edge2.getNode2().equals(startEdge.getNode1()))
                                    || (edge2.getNode2().equals(edge.getNode2()) && edge2.getNode1().equals(startEdge.getNode1())))
                                return true;
                    }
                }
                else
                if(startEdge.getNode2().equals(edge.getNode2())){
                    for(Edge edge2 : player.getEdges()){
                        if(!edge2.equals(edge) && !edge2.equals(startEdge))
                            if((edge2.getNode1().equals(edge.getNode1()) && edge2.getNode2().equals(startEdge.getNode1()))
                                    || (edge2.getNode2().equals(edge.getNode1()) && edge2.getNode1().equals(startEdge.getNode1())))
                                return true;
                    }
                }
            }
        }
        return false;
    }

}
