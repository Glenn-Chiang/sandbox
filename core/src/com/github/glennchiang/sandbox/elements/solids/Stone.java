package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.solids.Solid;

public class Stone extends Solid {
    private static final int durability = 5;
    public Stone(Grid grid) {
        super(grid, durability);
    }

    @Override
    public void onContactAcid() {
        // corrode
    }
}
