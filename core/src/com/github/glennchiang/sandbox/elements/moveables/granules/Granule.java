package com.github.glennchiang.sandbox.elements.moveables.granules;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Liquid;

import java.util.ArrayList;
import java.util.List;

public abstract class Granule extends MovableElement {
    protected final int fallRate; // Number of cells by which the element will move down per frame

    public Granule(Grid grid) {
        super(grid);
        fallRate = getFallRate();
    }

    @Override
    protected void update(int row, int col) {
        if (fall(Direction.DOWN)) return;
        List<Move> moves = new ArrayList<>();
        Move downLeft = () -> fall(Direction.DOWN_LEFT);
        Move downRight = () -> fall(Direction.DOWN_RIGHT);
        moves.add(downLeft);
        moves.add(downRight);
        randomMove(moves);
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
}
