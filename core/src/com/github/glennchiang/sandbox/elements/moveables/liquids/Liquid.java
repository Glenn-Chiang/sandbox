package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.solids.Solid;

public abstract class Liquid extends MovableElement {
    protected final int flowRate; // Number of cells by which the element will move horizontally per frame

    public Liquid(Grid grid) {
        super(grid);
        flowRate = getFlowRate();
    }

    @Override
    protected void update(int row, int col) {
        if (fall(Direction.DOWN)) return;
        if (fall(Direction.DOWN_LEFT)) return;
        if (fall(Direction.DOWN_RIGHT)) return;
        if (move(Direction.LEFT, flowRate)) return;
        if (move(Direction.RIGHT, flowRate)) return;
    }

    protected abstract int getFlowRate();

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
    protected boolean sinksIn(Element element) {
        return false;
    }
}
