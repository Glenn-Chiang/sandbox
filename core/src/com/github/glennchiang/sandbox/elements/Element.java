package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.Fire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public final void destroy() {
        grid.destroyElement(row, col);
        destroyed = true;
    }

    // The world grid will call this method on every render loop
    // Update the latest position of the element given by the grid
    public final void updateSelf(int row, int col) {
        this.row = row;
        this.col = col;

        update();

        if (isBurning) burn();
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

    protected final boolean isNeighbour(ElementType elementType, int count) {
        for (Direction dir: Direction.values()) {
            if (hasNeighbor(dir) && elementType == getNeighbor(dir).getElementType()) {
                count--;
            }
        }
        return count <= 0;
    }
    // Check if this element is adjacent to at least [count] number of the given element

    // Check if any of this element's immediate neighbours is an instance of the given element class
    protected final boolean isNeighbour(ElementType elementType) {
        return isNeighbour(elementType, 1);

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

    // How the element will react with fire
    public void onContactFire(Fire fire) {
        if (flammable) burn();
    }

    private void burn() {
        // Keep track of elements that have already been visited by the burning algorithm to avoid infinite loop
        Map<Element, Boolean> visited = new HashMap<>();
        recursiveBurn(visited);
    }

    private void recursiveBurn(Map<Element, Boolean> visited) {
        // Skip this element if already visited
        if (visited.getOrDefault(this, false)) return;
        // Skip this element if it is not flammable
        if (!flammable) return;

        isBurning = true;
        takeDamage(Fire.burnDamage);
        visited.put(this, true); // Mark this element as visited

        // Spread fire down after being destroyed
        if (destroyed) {
            transformTo(ElementType.FIRE);
            if (hasNeighbor(Direction.DOWN)) {
                getNeighbor(Direction.DOWN).recursiveBurn(visited);
            }
            return;
        }

        // Flammable elements will spread fire to neighbors
        for (Element neighbor: getNeighbors(Fire.spreadDirections)) {
            if (Math.random() < 0.1) { // Chance to spread fire per frame
                neighbor.recursiveBurn(visited);
                break;
            }
        }
    }

    // Some elements like non-flammable liquids can douse burning elements to stop them from burning further
    public void douse(Element source) {
        if (!isBurning) return;

        Map<Element, Boolean> visited = new HashMap<>();
        visited.put(source, true);
        recursiveDouse(visited);
    }

    // Doused elements will also douse neighboring elements
    private void recursiveDouse(Map<Element, Boolean> visited) {
        if (visited.getOrDefault(this, false)) return;
        isBurning = false;
        visited.put(this, true);
        for (Element neighbor: getNeighbors()) {
            neighbor.recursiveDouse(visited);
        }
    }
}
