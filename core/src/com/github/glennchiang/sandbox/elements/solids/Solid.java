package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.liquids.Liquid;

public abstract class Solid extends Element {
    public Solid(Grid grid) {
        super(grid);
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
