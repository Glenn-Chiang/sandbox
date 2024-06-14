package com.github.glennchiang.sandbox.elements.fluids.granules;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Mud;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Water;

public class Dirt extends Granule {
    private static final int fallRate = 1;

    public Dirt(Grid grid) {
        super(grid, fallRate);
    }

    @Override
    protected void update() {
        super.update();
        if (isNeighbour(Water.class) || isNeighbour(Mud.class, 4)) {
            transformTo(ElementType.MUD);
        }
    }
}