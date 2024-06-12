package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Grid;

public abstract class Solid extends Element {
    public Solid(Grid grid) {
        super(grid);
    }
    @Override
    protected void update(int row, int col) {

    }

    @Override
    protected boolean sinksIn(Element element) {
        return element instanceof Liquid;
    }
}
