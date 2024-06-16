package com.github.glennchiang.sandbox.elements.fluids.liquids;

import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.List;

public class Acid extends Liquid {
    private static final int fallRate = 2;
    private static final int flowRate = 2;
    private static final boolean flammable = false;
    private static final int density = 2;

    // When acid comes into contact with an element that it can corrode,
    // the element will receive this amount of damage per frame
    private static final int corrosionDamage = 1;

    public Acid(Grid grid) {
        super(grid, flammable, fallRate, flowRate, density);
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

}
