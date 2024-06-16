package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.github.glennchiang.sandbox.Grid;

public class Steam extends Gas {
    private static final boolean flammable = false;
    private static final int floatRate = 2;
    private static final int flowRate = 1;
    private static final int density = 1;
    private static final float minLifespan = 6;
    private static final float maxLifespan = 8;
    public Steam(Grid grid) {
        super(grid, flammable, floatRate, flowRate, density, minLifespan, maxLifespan);

    }
}
