package org.example.Homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void startAllRobots() {
        for (Robot robot : robots) {
            robot.start();
        }
    }

    public void startRobot(String name) {
        for (Robot robot : robots) {
            if (robot.getName().equals(name)) {
                robot.start();
                return;
            }
        }
        System.out.println("Robot not found.");
    }

    public void pauseRobot(String name) {
        for (Robot robot : robots) {
            if (robot.getName().equals(name)) {
                robot.pause();
                return;
            }
        }
        System.out.println("Robot not found.");
    }

    public void pauseRobot(String name, int time) {
        for (Robot robot : robots) {
            if (robot.getName().equals(name)) {
                robot.sleep(time);
                return;
            }
        }
        System.out.println("Robot not found.");
    }

    public void pauseAllRobots() {
        for (Robot robot : robots) {
            robot.pause();
        }
    }

    public void stopAllRobots(){
        for (Robot robot : robots) {
            robot.stop();
        }
    }

    public void PrintTokensPerRobot(){
        for (Robot robot : robots) {
            List<Token> tokens = robot.getTokens();
            int count = tokens.size();
            System.out.println(robot.getName() + " placed " + count + " tokens.");
        }
    }
    public SharedMemory getSharedMemory() {
        return mem;
    }

    public List<Robot> getRobots() {
        return robots;
    }
    public static void main(String[] args) {
        var explore = new Exploration();
        explore.addRobot(new Robot("Wall-E", explore));
        explore.addRobot(new Robot("R2D2", explore));
        explore.addRobot(new Robot("Optimus Prime", explore));
        InputHandler inputHandler = new InputHandler(explore);
        inputHandler.start();
        TimeKeeper timeKeeper = new TimeKeeper(explore);
        timeKeeper.start();
        explore.start();
    }
}
