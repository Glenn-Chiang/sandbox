package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public abstract class Element {

    // Current position of this element in its grid
    protected final Grid grid;

    public ElementType getElementType() {
        return ElementType.valueOf(this.getClass().getSimpleName().toUpperCase());
    }

    public Color getColor() {
       return getElementType().color;
    }

    public Element(Grid grid) {
        this.grid = grid;
    }


    // In each update loop, the Grid will call this method for each element,
    // passing its latest position
    public abstract void update(int row, int col);
}
