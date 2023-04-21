package org.example.Compulsory;

import java.util.List;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    private Exploration explore;
    private SharedMemory sharedMemory;


    public Robot(String name, Exploration explore) {
        this.name = name;
        this.explore = explore;
        this.running = true;
        sharedMemory = explore.getSharedMemory();
    }

    public void stop() {
        this.running = false;
    }

    @Override
    public void run() {
        while (running) {
// Pick a new cell to explore
            int row = (int) (Math.random() * explore.getMap().getRows());
            int col = (int) (Math.random() * explore.getMap().getCols());
            // Visit the cell
            boolean visited = explore.getMap().visit(row, col, this);
            if (visited) {
                // Make the robot sleep for some time
                int sleepTime = (int) (Math.random() * 1000);
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("Robot " + name + " was interrupted while sleeping.");
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public synchronized List<Token> extractTokens(List<Token> tokens) {
        return sharedMemory.extractTokens(tokens.size());
    }

    public void addTokens(List<Token> tokens) {
        sharedMemory.addTokens(tokens);
    }
}

