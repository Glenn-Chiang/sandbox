package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Grid;

public class Mud extends Liquid {
    private static final boolean flammable = false;
    private static final int fallRate = 1;
    private static final int flowRate = 1;
    private static final int density = 4;
    public Mud(Grid grid) {
        super(grid, false, 1, 1, 4);
    }
}
