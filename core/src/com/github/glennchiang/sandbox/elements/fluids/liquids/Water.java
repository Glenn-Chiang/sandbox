package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;

public class Water extends Liquid {
    private static final int fallRate = 2;
    private static final int flowRate = 2;

    private static final int density = 2;
    private static final boolean flammable = false;

    public Water(Grid grid) {
        super(grid, flammable, fallRate, flowRate, density);
    }

    @Override
    public void onContactAcid() {
        // Water will become acid on contact with acid
        transformTo(ElementType.ACID);
    }
}
