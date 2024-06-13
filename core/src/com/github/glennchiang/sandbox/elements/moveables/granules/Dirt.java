package com.github.glennchiang.sandbox.elements.moveables.granules;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Water;

public class Dirt extends Granule {
    private static final int fallRate = 2;

    public Dirt(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }

    @Override
    protected void update() {
        super.update();
        if (isAdjacentTo(ElementType.WATER)) {
            transformTo(ElementType.MUD);
        }
    }
}
