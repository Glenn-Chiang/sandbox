package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.ElementType;
import com.github.glennchiang.sandbox.elements.fluids.Fire;

public class Oil extends Liquid {
    private static final int fallRate = 2;
    private static final int flowRate = 2;

    private static final int density = 1;
    private static final boolean flammable = true;

    public Oil(Grid grid) {
        super(grid, flammable, fallRate, flowRate, density);
    }
}
