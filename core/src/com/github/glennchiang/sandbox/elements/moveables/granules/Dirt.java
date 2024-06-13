package com.github.glennchiang.sandbox.elements.moveables.granules;

import com.github.glennchiang.sandbox.Grid;

public class Dirt extends Granule {
    private final int fallRate = 1;

    public Dirt(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }

    @Override
    protected void update(int row, int col) {
        super.update(row, col);
    }
}
