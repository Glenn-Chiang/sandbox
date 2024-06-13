package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.moveables.granules.Granule;
import com.github.glennchiang.sandbox.elements.solids.Solid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Liquid extends MovableElement {
    // Number of cells by which the element will move horizontally per frame
    protected final int flowRate;
    protected abstract int getFlowRate();

    // Determines which liquids sink in other liquids
    protected final int density;
    protected abstract int getDensity();

    public Liquid(Grid grid) {
        super(grid);
        flowRate = getFlowRate();
        density = getDensity();
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.DOWN)),
                Arrays.asList(() -> fall(Direction.DOWN_LEFT),
                        () -> fall(Direction.DOWN_RIGHT)),
                Arrays.asList(() -> move(Direction.LEFT, flowRate),
                        () -> move(Direction.RIGHT, flowRate))
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
        return false;
    }
}
