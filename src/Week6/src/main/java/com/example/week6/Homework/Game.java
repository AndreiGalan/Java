package com.example.week6.Homework;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Game implements Serializable {
    private List<Node> nodes;
    private List<Edge> edges;
    private Player player1 = new Player("Player 1", "red");
    private Player player2 = new Player("Player 2", "blue");
    private Player currentPlayer;
    private Boolean playerTurn = false;
    private Boolean gameOver = false;
    private String input;
    private boolean reset = false;
    private String fileName;
    Strategy strategy = new Strategy("First Triangle");
    private int nrEdges = 0;

    public Game() {
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        currentPlayer = player1;
        MainFrame mainFrame = new MainFrame(this);
        mainFrame.setVisible(true);
        while(true)
        {
            while (!gameOver) {
                System.out.println("Player with color " + getColor() + " can turn now!");
                playRound();
                gameOver = strategy.winCondition(player1) || strategy.winCondition(player2);
                if (noEdgesLeft()) {
                    break;
                }
            }

            if (!gameOver) {
                System.out.println("Draw!");
            } else {
                    if(strategy.winCondition(player1))
                    {
                        System.out.println("Player 1 wins!");
                    }
                    else
                    {
                        System.out.println("Player 2 wins!");
                    }
            }
            mainFrame.canvas.clear();

        }
    }



    public void resetGame() {
        System.out.println("Resetting game...");
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        player1 = new Player("Player 1", "red");
        player2 = new Player("Player 2", "blue");
        currentPlayer = player1;
        playerTurn = false;
        gameOver = false;
        input = "";
        reset = false;
        strategy = new Strategy("First Triangle");
        nrEdges = 0;
        new MainFrame(this);
    }

    public void newEdges()
    {
        edges = new ArrayList<Edge>();
        nrEdges = 0;
    }
    public String getColor()
    {
        return currentPlayer.getColor();
    }
    public List<Node> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Node getNodeByName(String name){
        for (Node node : nodes) {
            if(node.getName().equals(name)){
                return node;
            }
        }
        return null;
    }

    public void createEdges(int numNodes, double probability){
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                if (Math.random() < probability) {
                    Edge edge = new Edge("E " + nrEdges, nodes.get(i), nodes.get(j), "black");
                    edges.add(edge);
                    nodes.get(i).addNeighbor(nodes.get(j));
                    nodes.get(j).addNeighbor(nodes.get(i));
                    nrEdges += 1;
                }
            }
        }
    }

    public Edge getEdgeByName(String name){
        for (Edge edge : edges) {
            if(edge.getName().equals(name) && edge.getColor().equals("black")){
                return edge;
            }
        }
        return null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    public void playRound(){
        while(true){
            currentPlayer.turnOn();
            while(currentPlayer.getTurnOn()){
                //System.out.println("2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            break;
        }
        if(currentPlayer == player1){
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }

    }

    public Boolean noEdgesLeft(){
        for (Edge edge : edges) {
            if(edge.getColor().equals("black")){
                return false;
            }
        }
        return true;
    }

    public void saveGame() {
        try{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Game");
            fileChooser.setApproveButtonText("Save");

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                fileName = new String(fileToSave.getAbsolutePath());
                fileName = fileToSave.getAbsolutePath();
                if(!fileName.endsWith(".ser")){
                    fileName += ".ser";
                }

                FileOutputStream fileOut = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(nodes);
                out.writeObject(edges);
                out.writeObject(player1);
                out.writeObject(player2);
                out.writeObject(currentPlayer);
                out.writeBoolean(gameOver);
                out.close();
                fileOut.close();
                System.out.println("Game saved!");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadGame() {
        try {
            // create a file chooser dialog
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Load Game");
            fileChooser.setApproveButtonText("Load");

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                fileName = new String(fileToLoad.getAbsolutePath());
                String fileName = fileToLoad.getAbsolutePath();
                System.out.println("Loading game...");
                currentPlayer.turnOff();
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                nodes = (List<Node>) in.readObject();
                edges = (List<Edge>) in.readObject();
                player1 = (Player) in.readObject();
                player2 = (Player) in.readObject();
                currentPlayer = (Player) in.readObject();
                gameOver = in.readBoolean();
                in.close();
                fileIn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
}
