package org.yangdeal;

import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameOfLifePresenter {

    private final GameOfLifeView view;
    private boolean[][] currentGeneration;
    private TimerTask task;

    /**
     * Constructor
     * @param view The GameOfLifeView. Cannot be null.
     */
    public GameOfLifePresenter(final GameOfLifeView view) {
        if (view == null) {
            throw new IllegalArgumentException("view cannot be null");
        }
        this.view = view;
        this.currentGeneration = new boolean[view.getHeight()][view.getWidth()];
        final Random random = new Random();
        for (int row = 0; row < currentGeneration.length; ++row) {
            for (int col = 0; col < currentGeneration[row].length; ++col) {
                currentGeneration[row][col] = random.nextBoolean();
            }
        }
    }

    /**
     * Starts the task of progressing through generations and updating the view.
     */
    public void start() {
        task = new TimerTask() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void run() {
                currentGeneration = progressGeneration(currentGeneration);
                view.drawGeneration(currentGeneration);
            }
        };
        new Timer().schedule(task, 0, 15);
    }

    private boolean[][] progressGeneration(final boolean[][] generation) {
        final boolean[][] nextGeneration = cloneGeneration(generation);

        // Decide the fate of each cell
        for (int row = 0; row < generation.length; ++row) {
            for (int col = 0; col < generation[row].length; ++col) {
                final int numNeighbors = countNeighbors(generation, row, col);
                // If under-populated or over-populated, cell dies
                if ((numNeighbors < 2) || (numNeighbors > 3)) {
                    nextGeneration[row][col] = false;
                }
                // No change
                if (numNeighbors == 2) {
                    nextGeneration[row][col] = generation[row][col];
                }
                // Cell stays alive, or a new cell is born
                if (numNeighbors == 3) {
                    nextGeneration[row][col] = true;
                }
            }
        }

        return nextGeneration;
    }

    private boolean[][] cloneGeneration(final boolean originalGeneration[][]) {
        final boolean[][] newGeneration = new boolean[originalGeneration.length][];
        for (int row = 0; row < originalGeneration.length; ++row) {
            newGeneration[row] = Arrays.copyOf(originalGeneration[row], originalGeneration[row].length);
        }
        return newGeneration;
    }

    private int countNeighbors(final boolean[][] generation, final int row, final int col) {
        int numNeighbors = 0;

        // Look NW
        if ((row - 1 >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row - 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look W
        if ((row >= 0) && (col - 1 >= 0)) {
            numNeighbors = generation[row][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look SW
        if ((row + 1 < generation.length) && (col - 1 >= 0)) {
            numNeighbors = generation[row + 1][col - 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look S
        if ((row + 1 < generation.length) && (col < generation[0].length)) {
            numNeighbors = generation[row + 1][col] ? numNeighbors + 1 : numNeighbors;
        }
        // Look SE
        if ((row + 1 < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row + 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look E
        if ((row < generation.length) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look NE
        if ((row - 1 >= 0) && (col + 1 < generation[0].length)) {
            numNeighbors = generation[row - 1][col + 1] ? numNeighbors + 1 : numNeighbors;
        }
        // Look N
        if ((row - 1 >= 0) && (col < generation[0].length)) {
            numNeighbors = generation[row - 1][col] ? numNeighbors + 1 : numNeighbors;
        }

        return numNeighbors;
    }

    /**
     * Stops the task of progressing through generations. Can safely be called prior to calling start().
     */
    public void stop() {
        if (task == null) {
            return;
        }
        task.cancel();
    }

}