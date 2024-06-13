package com.github.glennchiang.sandbox.elements.moveables.liquids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.List;

public class Acid extends Liquid {
    private static final int fallRate = 2;
    @Override
    protected int getFallRate() { return fallRate; }
    private static final int flowRate = 2;
    @Override
    protected int getFlowRate() {
        return flowRate;
    }

    private static final int density = 1;
    @Override
    protected int getDensity() {
        return density;
    }

    public Acid(Grid grid) {
        super(grid);
        List<Element> neighbors = getNeighbors();
        for (Element neighbor: neighbors) {
            neighbor.onContactAcid();
        }
    }

    @Override
    public void onContactAcid() {
        return;
    }
}
