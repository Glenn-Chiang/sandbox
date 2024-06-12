package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.solids.Solid;

import java.util.ArrayList;
import java.util.List;

public abstract class Liquid extends MovableElement {
    protected final int flowRate; // Number of cells by which the element will move horizontally per frame

    public Liquid(Grid grid) {
        super(grid);
        flowRate = getFlowRate();
    }

    @Override
    protected void update(int row, int col) {
        if (fall(Direction.DOWN)) return;
        List<Move> moves = new ArrayList<>();
        Move downLeft = () -> fall(Direction.DOWN_LEFT);
        Move downRight = () -> fall(Direction.DOWN_RIGHT);
        moves.add(downLeft);
        moves.add(downRight);

        if (!randomMove(moves)) {
            moves.clear();
            Move flowLeft = this::flowLeft;
            Move flowRight = this::flowRight;
            moves.add(flowLeft);
            moves.add(flowRight);
            randomMove(moves);
        }
    }

    protected abstract int getFlowRate();

    private boolean flowLeft() {
        return move(Direction.LEFT, flowRate);
    }

    private boolean flowRight() {
        return move(Direction.RIGHT, flowRate);
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
        return false;
    }
}
