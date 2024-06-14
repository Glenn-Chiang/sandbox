package com.github.glennchiang.sandbox.elements.fluids;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.Arrays;
import java.util.List;

// TODO: Implement behaviour
public class Fire extends Fluid {
    private static final int lifespan = 40; // Number of frames fire will exist before it extinguishes
    private int life = lifespan; //Remaining lifespan

    public Fire(Grid grid) {
        super(grid, 0, 1, 0);
    }

    @Override
    protected void update() {
        super.update();
        // Life decreases every frame. When life reaches 0, fire extinguishes.
        life -= 1;
        if (life == 0) {
            destroy();
        }
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
    protected boolean sinksIn(Element element) {
        return false;
    }

    @Override
    public void onContactAcid() {}
}
