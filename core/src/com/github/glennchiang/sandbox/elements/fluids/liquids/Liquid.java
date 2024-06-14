package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;
import com.github.glennchiang.sandbox.elements.fluids.gas.Gas;
import com.github.glennchiang.sandbox.elements.fluids.granules.Granule;
import com.github.glennchiang.sandbox.elements.solids.Solid;

import java.util.Arrays;
import java.util.List;

public abstract class Liquid extends Fluid {
    // Determines which liquids sink in other liquids
    private final int density;
    protected abstract int getDensity();

    private static final int durability = 0;

    public Liquid(Grid grid, int fallRate, int flowRate) {
        super(grid, durability, fallRate, flowRate);
        density = getDensity();
    }

    @Override
    protected final List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.DOWN)),
                Arrays.asList(() -> fall(Direction.DOWN_LEFT),
                        () -> fall(Direction.DOWN_RIGHT)),
                Arrays.asList(() -> flow(Direction.LEFT),
                        () -> flow(Direction.RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();
    }

    @Override
    protected boolean tryStep(Direction dir) {
        // If possible, sink in the element at the target position
        if (sinksIn(getElementAt(dir))) {
            swap(dir);
            return true;
        }
        return false;
    }

    @Override
    protected boolean sinksIn(Element element) {
        if (element instanceof Solid || element instanceof Granule) {
            return false;
        }
        // Denser liquid will sink in less dense liquid
        if (element instanceof Liquid) {
            return density > ((Liquid) element).density;
        }
        return element instanceof Gas;
    }

}
