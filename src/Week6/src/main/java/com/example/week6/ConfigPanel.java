package com.example.week6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.HttpCookie;

public class ConfigPanel extends JPanel {

    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    public JSpinner dotsSpinner;
    public JComboBox linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        dotsLabel = new JLabel("Number of dots:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(3, 3, 100, 1));

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
            frame.canvas.repaint();
        });
    }

    public void setLinesCombo(JComboBox<Object> linesCombo) {
        this.linesCombo = linesCombo;
    }
}
