package com.example.week6.Homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    Game game;

    public ControlPanel(MainFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
        init();
    }
    private void init() {
        setLayout(new GridLayout(1, 4));
        add(loadBtn);
        add(saveBtn);
        add(resetBtn);
        add(exitBtn);
        exitBtn.addActionListener(this::exitGame);
        resetBtn.addActionListener(this::resetGame);
        saveBtn.addActionListener(this::saveGame);
        loadBtn.addActionListener(this::loadGame);

    }
    private void loadGame(ActionEvent e) {
        game.loadGame();
        frame.canvas.load();
    }

    private void saveGame(ActionEvent evt) {
        game.saveGame();
        frame.canvas.savePNG();
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void resetGame(ActionEvent e) {
        frame.canvas.repaint();
    }
}