package com.example.week6;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.util.Random;

public class DrawingPanel extends JPanel {

    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
    private List vertices;
    BufferedImage image;
    Graphics2D graphics;
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initPanel();
    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int numVertices = (int) frame.configPanel.dotsSpinner.getValue();

        double edgeProbability = (double) frame.configPanel.linesCombo.getSelectedItem();

        int[] x = new int[numVertices];
        int[] y = new int[numVertices];
        int r = 200;
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            x[i] = (int) (cx + r * Math.cos(angle));
            y[i] = (int) (cy + r * Math.sin(angle));
        }

        g.setColor(Color.RED);
        for (int i = 0; i < numVertices; i++) {
            g.fillOval(x[i] - 5, y[i] - 5, 10, 10);
        }

        g.setColor(Color.BLACK);
        Random random = new Random();
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (random.nextDouble() < edgeProbability) {
                    g.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }


    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void clear() {
        repaint();
    }


    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void setLineOption(Double lineOption) {
        this.edgeProbability = lineOption;
    }

    public Double getLineOption() {
        return edgeProbability;
    }
}
