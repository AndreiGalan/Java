package org.example.Homework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RobotController {
    private final Exploration exploration;
    private final Map<String, Robot> robotsByName;

    public RobotController(Exploration exploration) {
        this.exploration = exploration;
        this.robotsByName = new HashMap<>();
        for (Robot robot : exploration.getRobots()) {
            robotsByName.put(robot.getName(), robot);
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] tokens = line.split("\\s+");
            if (tokens.length == 0) {
                continue;
            }
            String command = tokens[0];
            if (command.equals("start")) {
                if (tokens.length == 1) {
                    startAllRobots();
                } else {
                    startRobot(tokens[1]);
                }
            } else if (command.equals("pause")) {
                if (tokens.length == 1) {
                    pauseAllRobots();
                } else {
                    pauseRobot(tokens[1]);
                }
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    private void startAllRobots() {
        for (Robot robot : exploration.getRobots()) {
            System.out.println("Starting " + robot.getName());
            new Thread(robot).start();
        }
    }

    private void startRobot(String name) {
        Robot robot = robotsByName.get(name);
        if (robot == null) {
            System.out.println("Robot not found");
        } else {
            new Thread(robot).start();
        }
    }

    private void pauseAllRobots() {
        for (Robot robot : exploration.getRobots()) {
            robot.stop();
        }
    }

    private void pauseRobot(String name) {
        Robot robot = robotsByName.get(name);
        if (robot == null) {
            System.out.println("Robot not found");
        } else {
            robot.stop();
        }
    }
}
