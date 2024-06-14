package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.github.glennchiang.sandbox.Grid;

public class Smoke extends Gas {
    private static final int floatRate = 1;
    private static final int flowRate = 1;
    public Smoke(Grid grid) {
        super(grid, floatRate, flowRate);
    }
}
