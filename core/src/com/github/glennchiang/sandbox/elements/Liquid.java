package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.utils.RandomUtils;

public abstract class Liquid extends Element {
    protected final int fallRate; // Number of cells by which the element will move down per frame
    protected final int flowRate; // Number of cells by which the element will move horizontally per frame

    public Liquid(Grid grid) {
        super(grid);
        fallRate = getFallRate();
        flowRate = getFlowRate();
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

    protected abstract int getFallRate();
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
