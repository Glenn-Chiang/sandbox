package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.Fire;

import java.util.ArrayList;
import java.util.List;

public abstract class Element {

    // Current position of this element in its grid
    protected final Grid grid;
    protected int row;
    protected int col;

    // The current condition or "health" of the element, initially set to the element's static durability
    // Taking damage reduces integrity. When integrity reaches 0, the element is destroyed.
    private int integrity;

    public final boolean flammable;
    private boolean isBurning = false;
    private boolean destroyed = false;

    public Element(Grid grid, int durability, boolean flammable) {
        this.grid = grid;
        integrity = durability;
        this.flammable = flammable;
    }
    public ElementType getElementType() {
        return ElementType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }

    public Color getColor() {
        if (isBurning) {
            return Color.CORAL;
        } else {
            return getElementType().color;
        }
    }

    public final void takeDamage(int damage) {
        integrity -= damage;
        if (integrity <= 0) {
            destroy();
        }
    }
    protected final void destroy() {
        grid.setElement(row, col, null);
        destroyed = true;
    }

    // The world grid will call this method on every render loop
    // Update the latest position of the element given by the grid
    public final void updateSelf(int row, int col) {
        this.row = row;
        this.col = col;

        if (flammable && isBurning) burn();

        update();
    }

    // Each element implements its own update behaviour
    protected abstract void update();

    protected final Element getNeighbor(Direction dir) {
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
            if (elementClass.isInstance(getNeighbor(dir))) {
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
            if (hasNeighbor(dir)) {
                neighbours.add(getNeighbor(dir));
            }
        }
        return neighbours;
    }

    protected final List<Element> getNeighbors(List<Direction> directions) {
        List<Element> neighbours = new ArrayList<>();
        for (Direction dir: directions) {
            if (hasNeighbor(dir)) {
                neighbours.add(getNeighbor(dir));
            }
        }
        return neighbours;
    }

    // Check if there is a neighboring element in this direction
    private boolean hasNeighbor(Direction dir) {
        return grid.inBounds(getCellPosition(dir)) && !isCellEmpty(dir);
    }

    // Replaces this element with the given element at its same position
    protected final void transformTo(ElementType elementType) {
        grid.setElement(row, col, elementType.createInstance(grid));
    }

    // How the element will react with acid
    public abstract void onContactAcid();

    public void acceptFire(Fire fire) { // Visitor pattern
        fire.react(this);
    }

    // How the element will react with fire
    public final void burn() {
        if (!flammable) return;

        isBurning = true;
        // Flammable elements will spread fire to neighbors
        List<Element> neighbors =  getNeighbors(Fire.spreadDirections);
        for (Element neighbor: neighbors) {
            if (Math.random() < 0.1) { // Chance to spread fire per frame
                neighbor.burn();
                break;
            }
        }

        takeDamage(Fire.burnDamage);
        if (destroyed) {
            transformTo(ElementType.FIRE);
            // Spread fire down after being destroyed
            if (hasNeighbor(Direction.DOWN)) {
                getNeighbor(Direction.DOWN).burn();
            }
        }
    }

    public void douse() {
        isBurning = false;
    }
}
