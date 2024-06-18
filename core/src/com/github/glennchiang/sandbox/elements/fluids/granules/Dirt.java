package com.github.glennchiang.sandbox.elements.fluids.granules;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Mud;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Water;

public class Dirt extends Granule {
    private static final boolean flammable = true;
    private static final int fallRate = 1;
    public Dirt(Grid grid) {
        super(grid, flammable, fallRate);
    }

    @Override
    protected void update() {
        super.update();
        // Dirt transforms into mud on contact with water or mud
        if (isNeighbour(ElementType.WATER) || isNeighbour(ElementType.MUD, 4)) {
            transformTo(ElementType.MUD);
        }
    }
}
