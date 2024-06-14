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

    // When acid comes into contact with an element that it can corrode,
    // the element will receive this amount of damage per frame
    private static final int corrosionDamage = 1;

    public Acid(Grid grid) {
        super(grid);
    }

    @Override
    protected void update() {
        super.update();
        // React with neighbouring elements
        List<Element> neighbors = getNeighbors();
        for (Element neighbor: neighbors) {
            neighbor.onContactAcid();
        }
    }

    public static void corrode(Element element) {
        element.takeDamage(corrosionDamage);
    }

    @Override
    public void onContactAcid() {
        return; // Acid shouldn't react with itself
    }
}
