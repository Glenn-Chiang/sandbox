package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Mud extends Liquid {
    private final int fallRate = 1;
    @Override
    protected int getFallRate() { return fallRate; }
    private final int flowRate = 1;
    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    public Mud(Grid grid) {
        super(grid);
    }
}
