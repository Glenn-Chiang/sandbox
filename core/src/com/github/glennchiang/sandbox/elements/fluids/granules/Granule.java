package com.github.glennchiang.sandbox.elements.fluids.granules;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;
import com.github.glennchiang.sandbox.elements.fluids.gas.Gas;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Acid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Liquid;

import java.util.Arrays;
import java.util.List;

public abstract class Granule extends Fluid {
    private static final int durability = 20;
    public Granule(Grid grid, int fallRate) {
        super(grid, durability, fallRate, 0);

    }

    @Override
    protected final List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.DOWN)),
                Arrays.asList(() -> fall(Direction.DOWN_LEFT),
                        () -> fall(Direction.DOWN_RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();
    }

    @Override
    protected boolean sinksIn(Element element) {
        // All granules sink in liquids
        return element instanceof Liquid || element instanceof Gas;
    }

    @Override
    public void onContactAcid() {
        Acid.corrode(this);
    }
}
