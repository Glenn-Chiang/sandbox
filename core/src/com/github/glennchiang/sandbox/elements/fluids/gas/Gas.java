package com.github.glennchiang.sandbox.elements.fluids.gas;

import com.badlogic.gdx.Gdx;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.fluids.Fluid;

import java.util.Arrays;
import java.util.List;

public abstract class Gas extends Fluid {
    private float life; //Remaining lifespan

    public Gas(Grid grid, boolean flammable, int floatRate, int flowRate, float lifespan) {
        super(grid, 0, flammable, floatRate, flowRate);
        life = lifespan;
    }

    @Override
    protected List<List<Move>> getMoves() {
        return Arrays.asList(
                Arrays.asList(() -> fall(Direction.UP),
                        () -> fall(Direction.UP_LEFT),
                        () -> fall(Direction.UP_RIGHT))
        );
    }

    @Override
    protected void update() {
        super.update();
        life -= Gdx.graphics.getDeltaTime();
        if (life <= 0) {
            destroy();
        }
    }

    @Override
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {

    }
}
