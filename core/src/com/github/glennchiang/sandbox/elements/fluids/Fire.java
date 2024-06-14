package com.github.glennchiang.sandbox.elements.fluids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.Arrays;
import java.util.List;

public class Fire extends Fluid {
    private static final int durability = 0;
    private static final int fallRate = 1;
    private static final int flowRate = 0;
    private static final boolean flammable = false;
    private static final int lifespan = 40; // Number of frames fire will exist before it extinguishes
    private int life = lifespan; //Remaining lifespan
    public static final int burnDamage = 1;

    public Fire(Grid grid) {
        super(grid, durability, flammable, fallRate, flowRate);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.UP),
                        () -> fall(Direction.UP_LEFT),
                        () -> fall(Direction.UP_RIGHT)),
                Arrays.asList(() -> fall(Direction.LEFT),
                        () -> fall(Direction.RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();
        // Life decreases every frame. When life reaches 0, fire extinguishes.
        life -= 1;
        if (life == 0) {
            destroy();
        }
        // React with neighbouring elements
        List<Element> neighbors = getNeighbors();
        for (Element neighbor: neighbors) {
            neighbor.onContactFire(this);
        }
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactFire(Fire fire) {}

    @Override
    public void onContactAcid() {}
}
