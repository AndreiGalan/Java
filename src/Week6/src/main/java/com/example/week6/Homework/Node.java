package com.example.week6.Homework;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {
    private static final long serialVersionUID = 2317541268047212185L;
    private String name;
    private Integer x;
    private Integer y;
    private List <Node> neighbors = new ArrayList<Node>();

    public Node(String name, Integer x, Integer y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return name;
    }
}
