package com.github.glennchiang.sandbox;

import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.Sand;

public class Grid {
    public final int numRows;
    public final int numCols;
    private final Element[][] grid;
    private final boolean[][] visitedTracker; // Matrix that keeps track of which cells have already been visited in the current render loop

    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        grid = new Element[numRows][numCols];
        visitedTracker = new boolean[numRows][numCols];
    }

    public Element elementAt(int row, int col) {
        return grid[row][col];
    }

    // Sets the element at the given cell to the given element
    // If there previously was an element at this cell, it will be lost
    public void setElement(int row, int col, Element element) {
        grid[row][col] = element;
    }

    public boolean isCellEmpty(int row, int col) {
        return grid[row][col] == null;
    }

    // Mark the given cell as having been visited in the current render loop
    private void markVisited(int row, int col) {
        visitedTracker[row][col] = true;
    }

    // Check whether the given cell has already been visited in the current render loop
    private boolean isVisited(int row, int col) {
        return visitedTracker[row][col];
    }

    // Mark all cells as not visited to prepare for the next render loop
    private void resetVisited() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                visitedTracker[row][col] = false;
            }
        }
    }

    // Update each element in the grid
    public void update() {
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Element element = grid[row][col];
                // Skip over cells that are empty or have already been visited
                if (element == null || isVisited(row, col)) {
                    continue;
                }
                element.update(this, row, col);
                markVisited(row, col);
                resetVisited();
            }
        }
    }
}
