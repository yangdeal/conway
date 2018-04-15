package org.yangdeal;

public interface GameOfLifeView {

    /**
     * Returns the width of drawable area.
     * @return The non-negative, non-zero width of drawable area.
     */
    public int getWidth();

    /**
     * Returns the height of drawable area.
     * @return The non-negative, non-zero height of drawable area.
     */
    public int getHeight();

    /**
     * Draws the given generation.
     * @param generation The generation to draw as a boolean[][]. Cannot be null.
     */
    public void drawGeneration(final boolean[][] generation);

}