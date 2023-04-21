package org.example.Compulsory;

import java.util.ArrayList;
import java.util.List;

public class Exploration {
    private final SharedMemory mem = new SharedMemory(10);
    private final ExplorationMap map = new ExplorationMap(10, 10);
    private final List<Robot> robots = new ArrayList<>();

    public void addRobot(Robot robot) {
        robots.add(robot);
    }

    public SharedMemory getMem() {
        return mem;
    }

    public ExplorationMap getMap() {
        return map;
    }

    public SharedMemory getMemory() {
        return mem;
    }

    public void start() {
        for (Robot robot : robots) {
            new Thread(robot).start();
        }
    }

    public static void main(String[] args) {
        var explore = new Exploration();
        explore.addRobot(new Robot("Wall-E", explore));
        explore.addRobot(new Robot("R2D2", explore));
        explore.addRobot(new Robot("Optimus Prime", explore));
        explore.start();
    }

    public SharedMemory getSharedMemory() {
        return mem;
    }
}
