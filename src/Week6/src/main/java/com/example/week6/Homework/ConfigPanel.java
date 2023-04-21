package com.example.week6.Homework;

import javax.swing.*;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    JButton createButton;
    Game game;
    public ConfigPanel(MainFrame frame, Game game){
        this.frame = frame;
        this.game = game;
        init();
    }

    private void init() {
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));

        linesLabel = new JLabel("Number of lines:");
        Double[] lineOptions = { 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 };
        linesCombo = new JComboBox<Double>(lineOptions);
        createButton = new JButton("Create new game");
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
        createButton.addActionListener(e -> {
            frame.canvas.clear();
        });
    }

    public void setLinesCombo(JComboBox<Object> linesCombo) {
        this.linesCombo = linesCombo;
    }
}
