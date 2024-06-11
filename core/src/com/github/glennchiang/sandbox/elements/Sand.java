package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Grid;

public class Sand extends Element {
    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    public void update(int row, int col) {
        // If below is empty, move down
        if (grid.isEmptyAt(row + 1, col)) {
            grid.moveElement(row, col, row + 1, col);
            return;
        }

        // If below is water, sink in water by swapping places with it
        if (grid.elementAt(row + 1, col) instanceof Water) {
            grid.swapElements(row, col, row + 1, col);
        }

        boolean leftDownEmpty = grid.isEmptyAt(row + 1, col - 1);
        boolean rightDownEmpty = grid.isEmptyAt(row + 1, col + 1);

        if (leftDownEmpty && !rightDownEmpty) {
            // Move left down
            grid.moveElement(row, col, row + 1, col - 1);
            return;
        }

        if (rightDownEmpty && !leftDownEmpty) {
            // Move right down
            grid.moveElement(row, col, row + 1, col + 1);
            return;
        }

        if (leftDownEmpty && rightDownEmpty) {
            // Randomly decide to move left down or right down
            if (Math.random() < 0.5) {
                grid.moveElement(row, col, row + 1, col - 1);
            } else {
                grid.moveElement(row, col, row + 1, col + 1);
            }
        }
    }
}
