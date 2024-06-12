package com.github.glennchiang.sandbox.elements.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Water extends Liquid {
    private final int fallRate = 1;
    private final int flowRate = 2;

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
    public void update(int row, int col) {

    }
}
