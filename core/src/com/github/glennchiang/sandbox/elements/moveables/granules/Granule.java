package com.github.glennchiang.sandbox.elements.moveables.granules;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Liquid;

public abstract class Granule extends MovableElement {
    protected final int fallRate; // Number of cells by which the element will move down per frame

    public Granule(Grid grid) {
        super(grid);
        fallRate = getFallRate();
    }

    @Override
    protected boolean sinksIn(Element element) {
        // All granules sink in liquids
        return element instanceof Liquid;
    }

    @Override
    protected boolean tryStep(Direction dir) {
        // If possible, sink in the element at the target position
        if (sinksIn(getElementAt(dir))) {
            swap(dir);
            return true;
        }
        return false;
    }

    @Override
    protected void update(int row, int col) {
        if (fall(Direction.DOWN)) return;
        if (fall(Direction.DOWN_LEFT)) return;
        fall(Direction.DOWN_RIGHT);
    }
}
