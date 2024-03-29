package com.example.week6.Homework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Player implements Serializable {
    private String name;
    private String color;
    private List <Edge> edges = new ArrayList<Edge>();
    private Boolean turnOn = false;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
        edge.setColor(color);
        turnOn = false;
    }

    public void turnOff(){
        turnOn = false;
    }

    public void turnOn(){
        turnOn = true;
    }

    public Boolean getTurnOn(){
        return turnOn;
    }

    public void reset() {
        edges.clear();
        turnOn = false;
    }
}
