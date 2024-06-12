package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

public abstract class Solid extends Element {
    public Solid(Grid grid) {
        super(grid);
    }

    @Override
    protected boolean tryMove(Direction dir) {
        return false;
    }
    @Override
    protected void update(int row, int col) {
        // Do nothing
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }
}
