package org.example.Homework;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Token> tokens;
    private boolean visited;

    public Cell() {
        this.tokens = new ArrayList<>();
        this.visited = false;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void addTokens(List<Token> newTokens) {
        tokens.addAll(newTokens);
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }


    @Override
    public String toString() {
        return "Cell{" +
                "tokens=" + tokens +
                ", visited=" + visited +
                '}';
    }

    public void setVisited(boolean b) {
        visited = b;
    }
}
