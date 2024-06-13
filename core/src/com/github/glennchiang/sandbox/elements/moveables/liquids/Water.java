package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;

public class Water extends Liquid {
    private static final int fallRate = 2;
    @Override
    protected int getFallRate() { return fallRate; }
    private static final int flowRate = 2;
    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    private static final int density = 1;
    @Override
    protected int getDensity() {
        return density;
    }

    public Water(Grid grid) {
        super(grid);
    }

    @Override
    protected void update() {
        super.update();
    }

    @Override
    public void onContactAcid() {
        transformTo(ElementType.ACID);
    }
}
