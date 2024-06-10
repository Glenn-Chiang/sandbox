package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public class Stone extends Element {
    public Stone(Grid grid) {
        super(grid);
    }
    public Color getColor() {
        return Color.GRAY;
    }
    @Override
    public void update(Grid grid, int row, int col) {
        // Stone simply stays in place
        return;
    }
}
