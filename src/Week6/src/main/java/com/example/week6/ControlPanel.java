package com.example.week6;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.*;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
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

    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }

    private void resetGame(ActionEvent e) {
        frame.canvas.clear();
    }
}