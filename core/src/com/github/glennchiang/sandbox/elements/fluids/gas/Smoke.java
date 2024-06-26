package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.github.glennchiang.sandbox.Grid;

public class Smoke extends Gas {
    private static final boolean flammable = false;
    private static final int floatRate = 1;
    private static final int flowRate = 0;
    private static final int density = 2;
    private static final float minLifespan = 4;
    private static final float maxLifespan = 5;
    public Smoke(Grid grid) {
        super(grid, flammable, floatRate, flowRate, density, minLifespan, maxLifespan);

    }
}
