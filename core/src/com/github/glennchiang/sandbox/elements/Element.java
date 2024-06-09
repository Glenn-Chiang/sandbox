package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public abstract class Element {
    protected final Grid grid; // The world grid that this element is contained in
    protected int row;
    protected int col;

    public abstract Color getColor();
    public Element(Grid grid) {
        this.grid = grid;
    }

    public abstract void update();
}
