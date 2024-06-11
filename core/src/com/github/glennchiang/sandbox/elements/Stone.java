package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;

public class Stone extends Element {
    public Stone(Grid grid) {
        super(grid);
    }
    @Override
    public void update(int row, int col) {
        // Stone simply stays in place
        return;
    }
}
