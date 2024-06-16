package com.github.glennchiang.sandbox.elements.solids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Acid;

public class Wood extends Solid {
    private static final int durability = 120;
    private static final boolean flammable = true;
    public Wood(Grid grid) {
        super(grid, durability, flammable);
    }

    @Override
    public void onContactAcid() {
        Acid.corrode(this);
    }
}
