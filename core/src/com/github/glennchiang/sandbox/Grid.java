package com.github.glennchiang.sandbox;

import com.github.glennchiang.sandbox.elements.Element;

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

    // Move element at initial position to next position. Initial position will become empty.
    public void moveElement(int initialRow, int initialCol, int nextRow, int nextCol) {
        // If next position is out of bounds, don't move
        if (!inBounds(nextRow, nextCol)) {
            return;
        }
        setElement(nextRow, nextCol, elementAt(initialRow, initialCol));
        setElement(initialRow, initialCol, null);

        markVisited(initialRow, initialCol);
        markVisited(nextRow, nextCol);
    }

    public boolean isCellEmpty(int row, int col) {
        // If the position is out of bounds, we don't consider it to be empty
        if (!inBounds(row, col)) return false;
        return grid[row][col] == null;
    }

    // Check whether the given position is a valid cell within the grid boundaries
    public boolean inBounds(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    // Check whether the given cell has already been visited in the current render loop
    private boolean isVisited(int row, int col) {
        return visitedTracker[row][col];
    }

    // Mark the given cell as having been visited in the current render loop
    private void markVisited(int row, int col) {
        visitedTracker[row][col] = true;
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
                if (isCellEmpty(row, col) || isVisited(row, col)) {
                    continue;
                }
                element.update(row, col);
                markVisited(row, col);
            }
        }
        resetVisited();
    }
}
