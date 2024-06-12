package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.utils.RandomUtils;

public class Sand extends Solid {
    public Sand(Grid grid) {
        super(grid);
    }
    private final int fallSpeed = 1; // Max number of cells the element will fall per frame

    @Override
    public void update(int row, int col) {
        Direction targetDirection = Direction.DOWN;

        if (isCellEmpty(targetDirection)) {
            move(targetDirection, fallSpeed);
            return;
        }

        // If below is water, sink in water by swapping places with it
        if (getElementAt(targetDirection) instanceof Water) {
            swap(targetDirection);
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

        move(targetDirection, fallSpeed);
    }
}
