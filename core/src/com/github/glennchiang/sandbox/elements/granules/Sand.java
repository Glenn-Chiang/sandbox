package com.github.glennchiang.sandbox.elements.granules;

import com.github.glennchiang.sandbox.Grid;

public class Sand extends Granule {
    private final int fallRate = 1;
    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }
}
