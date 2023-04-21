package com.example.week6.Homework;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private int numVertices;
    private double edgeProbability;
    private int[] x, y;
    Node node;
    Game game;
    int[] existingLines;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    public DrawingPanel(MainFrame frame, Game game) {
        this.frame = frame;
        this.game = game;
        createOffscreenImage();
        createBoard();
        initPanel();

    }
    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        this.addMouseListener(new MouseAdapter() {
            private boolean isPressed = false;
            @Override
            public void mousePressed(MouseEvent e) {
                //remove mouse click event
                if(!isPressed)
                {
                    isPressed = true;
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    for (int i = 0; i < numVertices; i++) {
                        for (int j = i + 1; j < numVertices; j++) {
                            if (existingLines[i * numVertices + j] == 1) {
                                int x1 = x[i];
                                int y1 = y[i];
                                int x2 = x[j];
                                int y2 = y[j];

                                // Calculate the distance from the mouse click event to the line
                                double distance = Math.abs((y2 - y1) * mouseX - (x2 - x1) * mouseY + x2 * y1 - y2 * x1)
                                        / Math.sqrt(Math.pow(y2 - y1, 2) + Math.pow(x2 - x1, 2));

                                // If the distance is less than 5 pixels (a threshold value), color the line
                                if (distance < 5) {

                                    String color = game.getCurrentPlayer().getColor();
                                    System.out.println("Color: " + color);
                                    if(color.equals("red"))
                                        graphics.setColor(Color.RED);
                                    else
                                        if(color.equals("blue"))
                                            graphics.setColor(Color.BLUE);
                                    for(Edge edge : game.getEdges())
                                    {
                                        if(edge.getName().equals("Edge" + i + j) && !edge.getColor().equals("black"))
                                        {
                                            System.out.println("Edge already exists!");
                                            return;
                                        }
                                    }
                                    game.getCurrentPlayer().addEdge(game.getEdgeByName("Edge" + i + j));
                                    graphics.drawLine(x1, y1, x2, y2);
                                    repaint();
                                    System.out.println(game.getEdges());
                                    return;
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                isPressed = false;
            }
        });
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }
    final void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }
    private void createVertices() {
        int x0 = W / 2; int y0 = H / 2; //middle of the board
        int radius = H / 2 - 10; //board radius
        double alpha = 2 * Math.PI / numVertices; // the angle
        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) (radius * Math.cos(alpha * i));
            y[i] = y0 + (int) (radius * Math.sin(alpha * i));
            node = new Node("Node"+i, x[i], y[i]);
            game.getNodes().add(node);
        }

    }

    private void drawVertices() {
        graphics.setColor(Color.RED);
        for (int i = 0; i < numVertices; i++) {
            graphics.fillOval(x[i] - 5, y[i] - 5, 10, 10);
        }
    }
    private void drawLines() {
        graphics.setColor(Color.BLACK);
        existingLines = new int[2* numVertices * numVertices /2];
        Random random = new Random();
        game.newEdges();
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (random.nextDouble() < edgeProbability) {
                    existingLines[i * numVertices + j] = 1;
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                    game.getEdges().add(new Edge("Edge"+i+j, game.getNodeByName("Node"+i), game.getNodeByName("Node"+j),"black"));
                }
            }
        }
    }

    private void drawVerticesLoaded() {
        graphics.setColor(Color.RED);
        x = new int[numVertices];
        y = new int[numVertices];
        for (Node node : game.getNodes()) {
            graphics.fillOval(node.getX() - 5, node.getY()- 5, 10, 10);
            x[node.getName().charAt(4) - '0'] = node.getX();
            y[node.getName().charAt(4) - '0'] = node.getY();
        }
    }
    private void drawLinesLoaded() {
        Random random = new Random();
        for (Edge edge : game.getEdges()) {
            if(edge.getColor().equals("red"))
                graphics.setColor(Color.RED);
            else if(edge.getColor().equals("blue"))
                graphics.setColor(Color.BLUE);
            else
                graphics.setColor(Color.BLACK);
            graphics.drawLine(edge.getNode1().getX(), edge.getNode1().getY(), edge.getNode2().getX(), edge.getNode2().getY());
        }

        for (Edge edge : game.getEdges()) {
            int i = edge.getNode1().getName().charAt(4) - '0';
            int j = edge.getNode2().getName().charAt(4) - '0';
            existingLines[i * numVertices + j] = 1;
        }
    }
    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }


    public void clear() {
        //game.getCurrentPlayer().turnOff();
        game.resetGame();
        createBoard();
    }

    public void savePNG() {
        try {
            String fileName = game.getFileName();
            fileName = fileName.substring(0, fileName.length() - 4);
            ImageIO.write(image, "PNG",
                    new File(fileName + ".png"));
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public void load() {
        createOffscreenImage();
        numVertices = game.getNodes().size();
        existingLines = new int[2* numVertices * numVertices /2];
        this.drawVerticesLoaded();
        this.drawLinesLoaded();
        repaint();
    }
}
