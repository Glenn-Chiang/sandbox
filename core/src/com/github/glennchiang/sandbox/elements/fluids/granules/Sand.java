package com.github.glennchiang.sandbox.elements.fluids.granules;

import com.github.glennchiang.sandbox.Grid;

public class Sand extends Granule {
    private static final int fallRate = 1;
    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }

    @Override
    protected void update() {
        super.update();
    }
}
