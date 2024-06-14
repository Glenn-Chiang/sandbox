package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Acid;

public class Stone extends Solid {
    private static final int durability = 200;
    private static final boolean flammable = false;
    public Stone(Grid grid) {
        super(grid, 200, false);
    }

    @Override
    public void onContactAcid() {
        Acid.corrode(this);
    }
}
