package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public class Sand extends Element {
    public Sand(Grid grid) {
        super(grid);
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }

    @Override
    public void update(Grid grid, int row, int col) {
        super.update(grid, row, col);
    }
}
