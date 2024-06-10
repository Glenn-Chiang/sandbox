package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public class Sand extends Element {
    private final float fallInterval = 0.01f; // The element will fall by 1 cell every interval, in seconds
    private float fallTimer = fallInterval; // The remaining time, in seconds, until the element will next fall

    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    public Color getColor() {
        return Color.valueOf("F7DC6F") ;
    }

    @Override
    public void update(int row, int col) {
        fallTimer -= Gdx.graphics.getDeltaTime();
        if (fallTimer > 0) return; // Do nothing if it is not yet time to fall

        fallTimer = fallInterval; // When timer reaches 0, reset timer

        // If below is empty, move down
        if (grid.isCellEmpty(row + 1, col)) {
            grid.moveElement(row, col, row + 1, col);
            return;
        }

        boolean leftDownEmpty = grid.isCellEmpty(row + 1, col - 1);
        boolean rightDownEmpty = grid.isCellEmpty(row + 1, col + 1);

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
