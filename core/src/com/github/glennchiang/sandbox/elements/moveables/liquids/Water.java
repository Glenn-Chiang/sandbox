package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Water extends Liquid {
    private final int fallRate = 1;
    private final int flowRate = 1;

    public Water(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }

    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    @Override
    protected void update(int row, int col) {
        super.update(row, col);
    }
}
