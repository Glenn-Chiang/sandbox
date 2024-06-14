package com.github.glennchiang.sandbox.elements.fluids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.List;

// TODO: Implement behaviour
public class Fire extends Fluid {
    private static final int durability = 0;

    public Fire(Grid grid) {
        super(grid, durability, 0, 0);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return null;
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
