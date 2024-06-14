package com.github.glennchiang.sandbox.elements.moveables.granules;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Acid;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Liquid;

import java.util.Arrays;
import java.util.List;

public abstract class Granule extends MovableElement {
    private static final int durability = 1;
    public Granule(Grid grid) {
        super(grid, durability);
    }

    @Override
    protected List<List<Move>> getMoves() {
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
        return element instanceof Liquid;
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
    public void onContactAcid() {

    }
}
