package com.github.glennchiang.sandbox.elements.fluids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.ElementType;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Liquid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Oil;

import java.util.Arrays;
import java.util.List;

public class Fire extends Fluid {
    private static final int durability = 0;
    private static final int fallRate = 1;
    private static final int flowRate = 1;
    private static final boolean flammable = false;
    private static final float minLifespan = 1;
    private static final float maxLifespan = 2;
    private float lifespan; //Remaining time until it extinguishes
    public static final int burnDamage = 1;

    public static final List<Direction> spreadDirections = Arrays.asList(Direction.UP, Direction.UP_LEFT,
                                                            Direction.UP_RIGHT, Direction.LEFT, Direction.RIGHT);

    public Fire(Grid grid) {
        super(grid, durability, flammable, fallRate, flowRate);
        // Randomly determine the lifespan of each Fire instance
        lifespan = MathUtils.random(minLifespan, maxLifespan);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
               Arrays.asList(() -> fall(Direction.UP),
                        () -> fall(Direction.UP_LEFT),
                        () -> fall(Direction.UP_RIGHT),
                        () -> flow(Direction.LEFT),
                        () -> flow(Direction.RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();

        // Life decreases every frame. When life reaches 0, fire extinguishes to smoke.
        lifespan -= Gdx.graphics.getDeltaTime();
        if (lifespan <= 0) {
            transformTo(ElementType.SMOKE);
            return;
        }

        // Neighboring elements decide how they react with fire
        for (Element neighbor: getNeighbors(spreadDirections)) {
            neighbor.onContactFire(this);
        }
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {}
}
