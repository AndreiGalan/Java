package org.example.Homework;

import java.util.Collection;
import java.util.List;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    private boolean paused;
    private boolean indefinitePause;
    private long pauseDuration;
    private Exploration explore;
    private SharedMemory sharedMemory;
    private final Object lock = new Object();


    public Robot(String name, Exploration explore) {
        this.name = name;
        this.explore = explore;
        this.running = true;
        this.paused = false;
        this.indefinitePause = false;
        this.pauseDuration = 0;
        sharedMemory = explore.getSharedMemory();
    }

    public void stop() {
        this.running = false;
    }

    public void pause() {
        System.out.println("Robot " + name + " paused.");
        this.paused = true;
    }

    public void start() {
        System.out.println("Robot " + name + " started.");
        this.paused = false;

        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void run() {
        while (running) {
            if (paused) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        System.out.println("Robot " + name + " was interrupted while waiting.");
                    }
                }
            } else {

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
                boolean allVisited = true;
                for (int i = 0; i < explore.getMap().getRows(); i++) {
                    for (int j = 0; j < explore.getMap().getCols(); j++) {
                        if (!explore.getMap().getCell(i, j).isVisited()) {
                            allVisited = false;
                            break;
                        }
                    }
                    if (!allVisited) {
                        break;
                    }
                }
                if (allVisited) {
                    System.out.println("Robot " + name + " has finished exploring the map.");
                    running = false;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void sleep(long duration) {
        System.out.println("Robot " + name + " is sleeping for " + duration + " seconds.");
        boolean wasPaused = this.paused;
        if (this.running) {
            this.pause();
        }
        duration = duration * 1000;
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            System.out.println("Robot " + name + " was interrupted while sleeping.");
        }
        if (this.running && !wasPaused) {
            this.start();
        }
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
    public Collection<Token> getTokens() {
        return sharedMemory.getTokens();
    }
}

