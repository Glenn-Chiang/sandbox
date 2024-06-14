package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

public abstract class Solid extends Element {
    public Solid(Grid grid, int durability) {
        super(grid, durability);
    }

    @Override
    protected void update() {
        // Do nothing
    }
}
