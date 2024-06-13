package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Water extends Liquid {
    private final int fallRate = 1;
    @Override
    protected int getFallRate() { return fallRate; }
    private final int flowRate = 2;
    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    public Water(Grid grid) {
        super(grid);
    }

    @Override
    protected void update() {
        super.update();
    }
}
