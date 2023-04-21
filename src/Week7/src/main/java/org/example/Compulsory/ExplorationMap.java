package org.example.Compulsory;

import java.util.Arrays;
import java.util.List;

public class ExplorationMap {
    private final Cell[][] matrix;

    public ExplorationMap(int rows, int cols) {
        this.matrix = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = new Cell();
            }
        }
    }

    public synchronized boolean visit(int row, int col, Robot robot) {
        Cell cell = matrix[row][col];
        synchronized (cell) {
            if (!cell.isVisited()) {
                List<Token> tokens = robot.extractTokens(cell.getTokens());
                robot.addTokens(tokens);
                cell.addTokens(tokens);
                System.out.printf("%s has visited cell (%d,%d)\n", robot.getName(), row, col);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i])).append("\n");
        }
        return sb.toString();
    }

    public double getRows() {
        return matrix.length;
    }

    public double getCols() {
        return matrix[0].length;
    }
}
