package com.github.glennchiang.sandbox.elements.fluids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.Arrays;
import java.util.List;

// TODO: Implement behaviour
public class Fire extends Fluid {
    private static final int durability = 0;

    public Fire(Grid grid) {
        super(grid, durability, 1, 0);
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
