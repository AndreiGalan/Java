package org.example.Homework;

import java.util.*;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    private boolean paused;
    private boolean indefinitePause;
    private long pauseDuration;
    private Exploration explore;
    private SharedMemory sharedMemory;
    private final Object lock = new Object();
    private int addedTokens = 0;
    private Queue<Cell> queue;


    public Robot(String name, Exploration explore) {
        this.name = name;
        this.explore = explore;
        this.running = true;
        this.paused = false;
        this.indefinitePause = false;
        this.pauseDuration = 0;
        sharedMemory = explore.getSharedMemory();
        this.queue = new LinkedList<>();
    }

    public void stop() {
        System.out.println("Robot " + name + " stopped.");
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

    public void increaseTokens()
    {
        this.addedTokens += 1;
    }

    public int getAddedTokens()
    {
        return this.addedTokens;
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
                int currentrow = (int) (Math.random() * explore.getMap().getRows());
                int currentcol = (int) (Math.random() * explore.getMap().getCols());
                List<Cell> frontier = new ArrayList<>(); // list of cells to explore
                frontier.add(explore.getMap().getCell(currentrow, currentcol));
                while (!frontier.isEmpty() && paused == false && running == true) {
                    Cell current = frontier.remove(0);
                    int row = -1, col = -1;
                    // find the row and col of the current cell
                    for (int i = 0; i < explore.getMap().getRows(); i++) {
                        for (int j = 0; j < explore.getMap().getCols(); j++) {
                            if (explore.getMap().getCell(i, j) == current) {
                                row = i;
                                col = j;
                                break;
                            }
                        }
                    }
                    boolean visited = explore.getMap().visit(row, col, this);
                    if (visited) {
                        // add the unvisited neighbors of the current cell to the frontier
                        if (row > 0 && !explore.getMap().getCell(row - 1, col).isVisited()) {
                            frontier.add(explore.getMap().getCell(row - 1, col));
                        }
                        if (row < explore.getMap().getRows() - 1 && !explore.getMap().getCell(row + 1, col).isVisited()) {
                            frontier.add(explore.getMap().getCell(row + 1, col));
                        }
                        if (col > 0 && !explore.getMap().getCell(row, col - 1).isVisited()) {
                            frontier.add(explore.getMap().getCell(row, col - 1));
                        }
                        if (col < explore.getMap().getCols() - 1 && !explore.getMap().getCell(row, col + 1).isVisited()) {
                            frontier.add(explore.getMap().getCell(row, col + 1));
                        }
                        int sleepTime = (int) (Math.random() * 1000);
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            System.out.println("Robot " + name + " was interrupted while sleeping.");
                        }

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
                    int count = getAddedTokens();
                    System.out.println("Robot " + name + " has finished exploring the map and has placed " + count + " tokens.");
                    running = false;
                }
                    // Make the robot sleep for some time
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
    public List<Token> getTokens() {
        return sharedMemory.getTokens();
    }
}

