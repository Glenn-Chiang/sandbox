package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public class Water extends Liquid {
    public Water(Grid grid) {
        super(grid);
    }

    @Override
    public void update(int row, int col) {
        // If below is empty, move down
        if (grid.isEmptyAt(row + 1, col)) {
            grid.moveElement(row, col, row + 1, col);
            return;
        }

        boolean leftDownEmpty = grid.isEmptyAt(row + 1, col - 1);
        boolean rightDownEmpty = grid.isEmptyAt(row + 1, col + 1);
        boolean leftEmpty = grid.isEmptyAt(row, col - 1);
        boolean rightEmpty = grid.isEmptyAt(row, col + 1);

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
            return;
        }

        if (leftEmpty && !rightEmpty) {
            // Move left
            grid.moveElement(row, col, row, col - 1);
            return;
        }

        if (rightEmpty && !leftEmpty) {
            // Move right
            grid.moveElement(row, col, row, col + 1);
            return;
        }

        if (leftEmpty && rightEmpty) {
            // Randomly decide to move left or right
            if (Math.random() < 0.5) {
                grid.moveElement(row, col, row, col - 1);
            } else {
                grid.moveElement(row, col, row, col + 1);
            }
        }
    }
}
