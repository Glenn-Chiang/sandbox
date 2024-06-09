package com.github.glennchiang.sandbox;

public class Grid {
    public final int numRows;
    public final int numCols;

    private final Element[][] grid;
    public Grid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        grid = new Element[numRows][numCols];
    }

    public Element elementAt(int row, int col) {
        return grid[row][col];
    }

    public void setElementAt(int row, int col, Element element) {
        grid[row][col] = element;
    }
}
