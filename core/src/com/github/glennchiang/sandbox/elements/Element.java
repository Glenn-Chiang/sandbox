package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;

import java.util.ArrayList;
import java.util.List;

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


    // The current condition or "health" of the element, initially set to the element's static durability
    // Taking damage reduces integrity. When integrity reaches 0, the element is destroyed.
    private int integrity;

    public Element(Grid grid, int durability) {
        this.grid = grid;
        integrity = durability;
    }
    public final void takeDamage(int damage) {
        integrity -= damage;
        if (integrity <= 0) {
            destroy();
        }
    }
    private void destroy() {
        grid.setElement(row, col, null);
    }

    // The world grid will call this method on every render loop
    // Update the latest position of the element given by the grid
    public final void updateSelf(int row, int col) {
        this.row = row;
        this.col = col;
        update();
    }

    // Each element implements its own update behaviour
    protected abstract void update();

    protected final Element getElementAt(Direction dir) {
        return grid.elementAt(row + dir.y, col + dir.x);
    }

    protected final boolean isCellEmpty(Direction dir) {
        return grid.isEmptyAt(row + dir.y, col + dir.x);
    }

    // Get the position of the cell that is 1 cell in the given direction
    // with respect to this element
    protected final CellPosition getCellPosition(Direction dir) {
        return new CellPosition(row + dir.y, col + dir.x);
    }

    // Check if this element is adjacent to at least [count] number of the given element
    protected final boolean isNeighbour(Class<? extends Element> elementClass, int count) {
        for (Direction dir: Direction.values()) {
            if (elementClass.isInstance(getElementAt(dir))) {
                count--;
            }
        }
        return count <= 0;
    }

    // Check if any of this element's immediate neighbours is an instance of the given element class
    protected final boolean isNeighbour(Class<? extends Element> elementClass) {
        return isNeighbour(elementClass, 1);
    }

    // Get neighboring elements
    protected final List<Element> getNeighbors() {
        List<Element> neighbours = new ArrayList<>();
        for (Direction dir: Direction.values()) {
            if (grid.inBounds(getCellPosition(dir)) && !isCellEmpty(dir)) {
                neighbours.add(getElementAt(dir));
            }
        }
        return neighbours;
    }

    // Replaces this element with the given element at its same position
    protected final void transformTo(ElementType elementType) {
        grid.setElement(row, col, elementType.createInstance(grid));
    }

    // What will happen to the element on contact with acid
    public abstract void onContactAcid();
}
