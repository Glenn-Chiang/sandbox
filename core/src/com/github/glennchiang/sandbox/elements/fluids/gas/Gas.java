package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Liquid;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public abstract class Gas extends Fluid {
    // Determines which gasses float/sink in other gases
    private final int density;
    private float lifespan; // Remaining time until the Gas disappears

    public Gas(Grid grid, boolean flammable, int floatRate, int flowRate, int density, float minLifespan, float maxLifespan) {
        super(grid, 0, flammable, floatRate, flowRate);
        this.density = density;
        lifespan = MathUtils.random(minLifespan, maxLifespan);
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.UP),
                        () -> fall(Direction.UP_LEFT),
                        () -> fall(Direction.UP_RIGHT)),
                Arrays.asList(() -> flow(Direction.LEFT),
                        () -> flow(Direction.RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();
        lifespan -= Gdx.graphics.getDeltaTime();
        if (lifespan <= 0) {
            destroy();
        }
    }

    @Override
    protected boolean sinksIn(Element element) {
        if (!(element instanceof Gas)) return false;
        return density > ((Gas) element).density;
    }

    @Override
    public void onContactAcid() {}
}
