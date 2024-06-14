package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;

import java.util.List;

public abstract class Gas extends Fluid {
    private static final int durability = 0;

    public Gas(Grid grid) {
        super(grid, durability);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return null;
    }

    @Override
    protected int getFallRate() {
        return 0;
    }

    @Override
    protected boolean tryStep(Direction dir) {
        return false;
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {

    }
}
