package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.utils.RandomUtils;

public class Water extends Liquid {
    private final int fallRate = 1;
    private final int flowRate = 2;

    public Water(Grid grid) {
        super(grid);
    }

    @Override
    protected int getFallRate() {
        return fallRate;
    }

    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    @Override
    public void update(int row, int col) {

    }
}
