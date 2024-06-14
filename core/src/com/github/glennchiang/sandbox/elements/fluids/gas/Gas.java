package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;

import java.util.Arrays;
import java.util.List;

public abstract class Gas extends Fluid {
    private static final int durability = 0;

    public Gas(Grid grid, int floatRate, int flowRate) {
        super(grid, durability, floatRate, flowRate);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.UP),
                        () -> fall(Direction.UP_LEFT),
                        () -> fall(Direction.UP_RIGHT))
        );
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {

    }
}
