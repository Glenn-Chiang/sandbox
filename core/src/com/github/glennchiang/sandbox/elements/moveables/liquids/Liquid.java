package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.moveables.MovableElement;
import com.github.glennchiang.sandbox.elements.solids.Solid;
import com.github.glennchiang.sandbox.utils.RandomUtils;

public abstract class Liquid extends MovableElement {
    protected final int flowRate; // Number of cells by which the element will move horizontally per frame

    public Liquid(Grid grid) {
        super(grid);
        flowRate = getFlowRate();
    }

    @Override
    protected boolean tryMove(Direction dir) {
        // If possible, sink in the element at the target position
        if (sinksIn(getElementAt(dir))) {
            swap(dir);
            return true;
        }
        return false;
    }

    @Override
    protected void update(int row, int col) {
        Direction targetDirection = Direction.DOWN;

        if (isCellEmpty(targetDirection)) {
            move(targetDirection, fallRate);
            return;
        }

        boolean canMoveDownLeft = isCellEmpty(Direction.DOWN_LEFT);
        boolean canMoveDownRight = isCellEmpty(Direction.DOWN_RIGHT);

        if (canMoveDownLeft && canMoveDownRight) {
            targetDirection = RandomUtils.selectRandom(Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
        }
        else if (canMoveDownLeft) {
            targetDirection = Direction.DOWN_LEFT;
        }
        else if (canMoveDownRight) {
            targetDirection = Direction.DOWN_RIGHT;
        }

        move(targetDirection, fallRate);
    }

    protected abstract int getFlowRate();

    @Override
    protected boolean sinksIn(Element element) {
        if (element instanceof Solid) return false;
        if (element instanceof Liquid) {
            // TODO: Check relative densities of liquids
            return true;
        }
        return false;
    }
}
