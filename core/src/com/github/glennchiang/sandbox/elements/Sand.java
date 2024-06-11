package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;

public class Sand extends Solid {
    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    public void update(int row, int col) {
        if (isCellEmpty(Direction.DOWN)) {
            grid.moveElement(row, col, row + 1, col);
            return;
        }

        // If below is water, sink in water by swapping places with it
        if (getElementAt(Direction.DOWN) instanceof Water) {
            grid.swapElements(row, col, row + 1, col);
        }

        boolean leftDownEmpty = isCellEmpty(Direction.DOWN_LEFT);
        boolean rightDownEmpty = isCellEmpty(Direction.DOWN_RIGHT);

        if (leftDownEmpty && rightDownEmpty) {
            // Randomly decide to move left down or right down
            if (Math.random() < 0.5) {
                grid.moveElement(row, col, row + 1, col - 1);
            } else {
                grid.moveElement(row, col, row + 1, col + 1);
            }
            return;
        }

        boolean leftEmpty = isCellEmpty(Direction.LEFT);
        boolean rightEmpty = isCellEmpty(Direction.RIGHT);
        boolean canSinkDownLeft = sinksIn(getElementAt(Direction.DOWN_LEFT)) && leftEmpty;
        boolean canSinkDownRight = sinksIn(getElementAt(Direction.DOWN_RIGHT)) && rightEmpty;


        if (leftDownEmpty) {
            // Move left down
            grid.moveElement(row, col, row + 1, col - 1);
            return;
        }

        if (rightDownEmpty) {
            // Move right down
            grid.moveElement(row, col, row + 1, col + 1);
        }

    }
}
