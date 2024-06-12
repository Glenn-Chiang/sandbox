package com.github.glennchiang.sandbox.elements.moveables;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

public abstract class MovableElement extends Element {
    protected final int fallRate; // Number of cells by which the element will move down per frame

    public MovableElement(Grid grid) {
        super(grid);
        fallRate = getFallRate();
    }

    protected abstract int getFallRate();

}
