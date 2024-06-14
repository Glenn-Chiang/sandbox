package com.github.glennchiang.sandbox.elements.fluids;

import com.badlogic.gdx.Gdx;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Liquid;

import java.util.Arrays;
import java.util.List;

public class Fire extends Fluid {
    private static final int durability = 0;
    private static final int fallRate = 1;
    private static final int flowRate = 1;
    private static final boolean flammable = false;
    private static final float lifespan = 1; // Number of seconds fire will exist before extinguishing
    private float life = lifespan; //Remaining lifespan
    public static final int burnDamage = 1;

    private static final List<Direction> spreadDirections = Arrays.asList(Direction.UP, Direction.UP_LEFT,
                                                            Direction.UP_RIGHT, Direction.LEFT, Direction.RIGHT);

    public Fire(Grid grid) {
        super(grid, durability, flammable, fallRate, flowRate);

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

        // Life decreases every frame. When life reaches 0, fire extinguishes.
        life -= Gdx.graphics.getDeltaTime();
        if (life <= 0) {
            destroy();
        }

        for (Element neighbor: getNeighbors()) {
            if (neighbor instanceof Liquid) {
                destroy();
            }
        }

        // Spread to neighboring elements
        for (Element neighbor: getNeighbors(spreadDirections)) {
            if (Math.random() < 0.5) { // Random chance to spread or not spread each frame
                neighbor.onContactFire();
                break;
            }
        }
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {}
}
