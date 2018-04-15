package org.yangdeal;

public class GameOfLifeViewStdDraw implements GameOfLifeView {

    private final int width;
    private final int height;

    /**
     * Constructor
     * @param width The width to use for the application window. Cannot be negative or zero.
     * @param height The height to use for the application window. Cannot be negative or zero.
     */
    public GameOfLifeViewStdDraw(final int width, final int height) {
        if (width < 1) {
            throw new IllegalArgumentException("width must be a positive value");
        }
        if (height < 1) {
            throw new IllegalArgumentException("height must be a positive value");
        }
        this.width = width;
        this.height = height;

        StdDraw.setCanvasSize(width, height);
        StdDraw.setYscale(0, height);
        StdDraw.setXscale(0, width);
        StdDraw.setPenRadius(0);
        StdDraw.setPenColor(StdDraw.BLACK);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawGeneration(final boolean[][] generation) {
        StdDraw.show(0);
        StdDraw.clear();
        for (int row = 0; row < generation.length; row++) {
            for (int col = 0; col < generation[row].length; col++) {
                if (generation[row][col] == true) {
                    StdDraw.point(col, row);
                }
            }
        }
        StdDraw.show(0);
    }

}