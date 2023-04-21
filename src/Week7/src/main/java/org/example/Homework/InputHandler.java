package org.example.Homework;

import java.util.Scanner;

public class InputHandler extends Thread {
    private final Exploration explore;

    public InputHandler(Exploration explore) {
        this.explore = explore;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] args = input.split(" ");
            if (args.length > 0) {
                String command = args[0];
                System.out.println(command);
                switch (command) {
                    case "start":
                        if (args.length == 1) {
                            explore.startAllRobots();
                        } else if (args.length == 2) {
                            String name = args[1];
                            explore.startRobot(name);
                        } else {
                            System.out.println("Invalid command.");
                        }
                        break;
                    case "pause":
                        if(args.length == 3)
                        {
                            String name = args[1];
                            int time = Integer.parseInt(args[2]);
                            explore.pauseRobot(name, time);
                        }
                        else
                        if (args.length == 2) {
                            String name = args[1];
                            explore.pauseRobot(name);
                        } else if(args.length == 1)
                        {
                            explore.pauseAllRobots();
                        }
                        else
                        {
                            System.out.println("Invalid command.");
                        }
                        break;
                    default:
                        System.out.println("Invalid command.");
                        break;
                }
            }
        }
    }
}
