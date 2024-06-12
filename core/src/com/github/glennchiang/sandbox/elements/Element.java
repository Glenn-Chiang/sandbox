package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;

public abstract class Element {

    // Current position of this element in its grid
    protected final Grid grid;
    protected int row;
    protected int col;

    public ElementType getElementType() {
        return ElementType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }

    public Color getColor() {
       return getElementType().color;
    }

    public Element(Grid grid) {
        this.grid = grid;
    }

    // The world grid will call this method on every render loop
    // Update the latest position of the element given by the grid
    public final void updateSelf(int row, int col) {
        this.row = row;
        this.col = col;
        update(row, col);
    }

    // Each element implements its own update behaviour
    protected abstract void update(int row, int col);

    protected final Element getElementAt(Direction dir) {
        return grid.elementAt(row + dir.y, col + dir.x);
    }

    protected final boolean isCellEmpty(Direction dir) {
        return grid.isEmptyAt(row + dir.y, col + dir.x);
    }

    // Get the position of the cell that is 1 unit(cell) in the given direction
    // with respect to this element
    protected final CellPosition getCellPosition(Direction dir) {
        return new CellPosition(row + dir.y, col + dir.x);
    }



}
