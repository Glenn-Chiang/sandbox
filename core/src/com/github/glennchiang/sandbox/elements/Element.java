package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
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
    public final void updateElement(int row, int col) {
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

    protected boolean sinksIn(Element element) {
        // TODO: Update this rule with more realistic logic
        return this instanceof Solid && element instanceof Liquid;
    }
}
