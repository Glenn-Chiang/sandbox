package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public abstract class Element {
    public ElementType elementType;

    // Current position of this element in its grid
    protected Grid grid;
    protected int row;
    protected int col;

    public abstract Color getColor();

    public Element() {}

    // In each update loop, the Grid will call this method for each element,
    // passing its latest position
    public void update(Grid grid, int row, int col) {
        this.grid = grid;
        this.row = row;
        this.col = col;
    }
}
