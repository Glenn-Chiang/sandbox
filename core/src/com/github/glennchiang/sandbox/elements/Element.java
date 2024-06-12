package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;

public abstract class Element {

    // Current position of this element in its grid
    private final Grid grid;
    private int row;
    private int col;

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

    protected Element getElementAt(Direction dir) {
        return grid.elementAt(row + dir.y, col + dir.x);
    }

    protected boolean isCellEmpty(Direction dir) {
        return grid.isEmptyAt(row + dir.y, col + dir.x);
    }

    // Get the position of the cell that is 1 unit(cell) in the given direction
    // with respect to this element
    protected CellPosition getCellPosition(Direction dir) {
        return new CellPosition(row + dir.y, col + dir.x);
    }

    // Move by 1 cell in the given direction if the move is possible
    private boolean move(Direction dir) {
        CellPosition targetPos = getCellPosition(dir);
        if (grid.inBounds(targetPos) && getElementAt(dir) != null) {
            grid.moveElement(row, col, targetPos.row, targetPos.col);
            return true;
        }
        return false;
    }

    // Distance: number of cells to move in the given direction
    protected void move(Direction dir, int distance) {
        for (int i = 0; i < distance; i++) {
            // Try to move this element by 1 cell in the given direction
            boolean canMove = move(dir);
            // If unable to move, stop trying to move further
            if (!canMove) return;
        }
    }

    protected void swap(Direction dir) {
        grid.swapElements(row, col, row + dir.y, col + dir.x);
    }



    // Check whether this element will sink in the given element
    protected abstract boolean sinksIn(Element element);

}
