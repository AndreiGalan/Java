package com.example.week6.Homework;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    Game game;

    public MainFrame(Game game){
        super("My Drawing Application");
        this.game = game;
        init();
    }



    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        configPanel = new ConfigPanel(this, game);
        canvas = new DrawingPanel(this, game);
        System.out.println("Welcome to the game!");
        controlPanel = new ControlPanel(this, game);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
    }


}