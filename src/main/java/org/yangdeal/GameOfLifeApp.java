package org.yangdeal;

public class GameOfLifeApp {

    /**
     * Creates a new GameOfLifeApp
     * @param width The width to use for the application window. Cannot be negative or zero.
     * @param height The height to use for the application window. Cannot be negative or zero.
     */
    public GameOfLifeApp(final int width, final int height) {
        if (width < 1) {
            throw new IllegalArgumentException("width must be positive");
        }
        if (height < 1) {
            throw new IllegalArgumentException("height must be positive");
        }
        final GameOfLifeView gameOfLifeView = new GameOfLifeViewStdDraw(width, height);
        final GameOfLifePresenter gameOfLifePresenter = new GameOfLifePresenter(gameOfLifeView);
        gameOfLifePresenter.start();
    }

    /**
     * Main method.
     * @param args The runtime args.
     */
    public static void main(final String... args) {
        // Optionally, you can get these dimensions at runtime from the user
        new GameOfLifeApp(500, 500);
    }

}