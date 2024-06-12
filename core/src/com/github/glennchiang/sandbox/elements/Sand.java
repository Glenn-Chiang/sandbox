package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.utils.RandomUtils;

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
