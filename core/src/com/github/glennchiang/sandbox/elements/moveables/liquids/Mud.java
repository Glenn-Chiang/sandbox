package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Mud extends Liquid {
    private static final int fallRate = 1;
    @Override
    protected int getFallRate() { return fallRate; }
    private static final int flowRate = 1;
    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    private static final int density = 4;
    @Override
    protected int getDensity() {
        return density;
    }

    public Mud(Grid grid) {
        super(grid);
    }
}
