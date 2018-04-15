package org.yangdeal;

import java.util.*;
//import edu.princeton.cs.introcs.org.yangdeal.StdDraw;


public class GameOfLifeAIO {
    public static void main(String[] args) {

        // the size of cells' array
        final int ROWS_NUM = 200;
        final int COLS_NUM = 200;

        Boolean[][] curGen = new Boolean[ROWS_NUM][COLS_NUM];

        // sets dead cells array
        for (int row = 0; row < ROWS_NUM; row++) {
            Arrays.fill(curGen[row], false);
        }

        // sets initial pattern - glider
        // curGen[149][150] = true;
        // curGen[150][151] = true;
        // curGen[151][149] = true;
        // curGen[151][150] = true;
        // curGen[151][151] = true;

        // fills array with random booleans, veeery slow
        Random random = new Random();
        for (int row = 0; row < ROWS_NUM; row++) {
            for (int col = 0; col < COLS_NUM; col++) {
                curGen[row][col] = random.nextBoolean();
            }
        }

        // initialises field for drawing cells
        StdDraw.setCanvasSize(COLS_NUM, ROWS_NUM);
        StdDraw.setYscale(0, ROWS_NUM);
        StdDraw.setXscale(0, COLS_NUM);
        StdDraw.setPenRadius(0);
        StdDraw.setPenColor(StdDraw.BLACK);

        // infinitely draws field
        while (true) {
            curGen = countNextGen(curGen, ROWS_NUM, COLS_NUM);
            StdDraw.show(0);
            StdDraw.clear();
            for (int row = 0; row < ROWS_NUM; row++) {
                for (int col = 0; col < COLS_NUM; col++) {
                    if (curGen[row][col] == true) {
                        StdDraw.point(col, row);
                    }
                }
            }
            StdDraw.show(0);
        }
    }

    // counts next generation
    public static Boolean[][] countNextGen(Boolean[][] curGen, int rowsNum, int colsNum) {

        // copies the current array of cells into temporary array so grid can
        // be changed without affecting the other cells
        Boolean[][] nextGen = new Boolean[rowsNum][];
        for (int row = 0; row < rowsNum; row++) {
            nextGen[row] = Arrays.copyOf(curGen[row], colsNum);
        }

        // decides what will happen to cell
        for (int row = 0; row < rowsNum; row++) {
            for (int col = 0; col < colsNum; col++) {

                int numOfNeighbours = countCellNeighbours(curGen, rowsNum, colsNum, row, col);

                // under or overpopulation, cell dies
                if ((numOfNeighbours < 2) || (numOfNeighbours > 3)) {
                    nextGen[row][col] = false;
                }

                // cell lives on to next generation
                if (numOfNeighbours == 2) {
                    nextGen[row][col] = curGen[row][col];
                }

                // cell either stays alive, or is born
                if (numOfNeighbours == 3) {
                    nextGen[row][col] = true;
                }
            }
        }
        return nextGen;
    }

    // counts cell's neighbours
    public static int countCellNeighbours(Boolean[][] curGen, int rowsNum, int colsNum, int row, int col) {
        int numOfNeighbours = 0;

        // decides which neighbour cells to count (for edge cells
        // checks for neighbours from opposite edge)
        // not edge cells
        if ((row > 0) && (row < rowsNum - 1) && (col > 0) && (col < colsNum - 1)) {
            if (curGen[row - 1][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][col + 1]) {
                numOfNeighbours++;
            }
            if (curGen[row][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row][col + 1]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col + 1]) {
                numOfNeighbours++;
            }
        }

        // top cells
        else if (row == 0) {

            // top-left cells
            if (col == 0) {

                // above
                if (curGen[rowsNum - 1][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][col + 1]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][col + 1]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[row + 1][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][col + 1]) {
                    numOfNeighbours++;
                }
            }

            // top-right cells
            else if (col == colsNum - 1) {

                // above
                if (curGen[rowsNum - 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][0]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][0]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[row + 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][0]) {
                    numOfNeighbours++;
                }
            }

            // top but not left or right
            else {

                // above
                if (curGen[rowsNum - 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[rowsNum - 1][col + 1]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][col + 1]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[row + 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row + 1][col + 1]) {
                    numOfNeighbours++;
                }
            }
        }

        //bottom cells
        else if (row == rowsNum - 1) {

            // bottom-left cells
            if (col == 0) {

                // above
                if (curGen[row - 1][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][col + 1]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][col + 1]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[0][colsNum - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[0][col]) {
                    numOfNeighbours++;
                }
                if (curGen[0][col + 1]) {
                    numOfNeighbours++;
                }
            }

            // bottom-right cells
            else if (col == colsNum - 1) {

                // above
                if (curGen[row - 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][0]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][0]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[0][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[0][col]) {
                    numOfNeighbours++;
                }
                if (curGen[0][0]) {
                    numOfNeighbours++;
                }
            }

            // bottom but not left or right
            else {

                // above
                if (curGen[row - 1][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][col]) {
                    numOfNeighbours++;
                }
                if (curGen[row - 1][col + 1]) {
                    numOfNeighbours++;
                }

                // same row
                if (curGen[row][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[row][col + 1]) {
                    numOfNeighbours++;
                }

                // below
                if (curGen[0][col - 1]) {
                    numOfNeighbours++;
                }
                if (curGen[0][col]) {
                    numOfNeighbours++;
                }
                if (curGen[0][col + 1]) {
                    numOfNeighbours++;
                }
            }
        }

        // left but not top or bottom cells
        else if (col == 0) {

            // above
            if (curGen[row - 1][colsNum - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][col + 1]) {
                numOfNeighbours++;
            }

            // same row
            if (curGen[row][colsNum - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row][col + 1]) {
                numOfNeighbours++;
            }

            // below
            if (curGen[row + 1][colsNum - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col + 1]) {
                numOfNeighbours++;
            }
        }

        // right but not top or bottom cells
        else if (col == colsNum - 1) {

            // above
            if (curGen[row - 1][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row - 1][0]) {
                numOfNeighbours++;
            }

            // same row
            if (curGen[row][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row][0]) {
                numOfNeighbours++;
            }

            // below
            if (curGen[row + 1][col - 1]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][col]) {
                numOfNeighbours++;
            }
            if (curGen[row + 1][0]) {
                numOfNeighbours++;
            }
        }
        return numOfNeighbours;
    }
}