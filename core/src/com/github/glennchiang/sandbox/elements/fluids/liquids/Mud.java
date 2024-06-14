package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Mud extends Liquid {
    private static final int fallRate = 1;
    private static final int flowRate = 1;
    private static final int density = 4;
    @Override
    protected int getDensity() {
        return density;
    }

    public Mud(Grid grid) {
        super(grid, fallRate, flowRate);
    }

    @Override
    public void onContactAcid() {
        return;
    }
}
