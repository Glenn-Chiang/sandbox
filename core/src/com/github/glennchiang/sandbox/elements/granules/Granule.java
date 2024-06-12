package com.github.glennchiang.sandbox.elements.granules;

import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.liquids.Liquid;
import com.github.glennchiang.sandbox.elements.liquids.Water;
import com.github.glennchiang.sandbox.utils.RandomUtils;

public abstract class Granule extends Element {
    protected final int fallRate; // Number of cells by which the element will move down per frame

    public Granule(Grid grid) {
        super(grid);
        fallRate = getFallRate();
    }
    protected abstract int getFallRate();

    @Override
    protected boolean sinksIn(Element element) {
        return element instanceof Liquid;
    }

    @Override
    public void update(int row, int col) {
        Direction targetDirection = Direction.DOWN;

        if (isCellEmpty(targetDirection)) {
            move(targetDirection, fallRate);
            return;
        }

        // If below is water, sink in water by swapping places with it
        if (getElementAt(targetDirection) instanceof Water) {
            swap(targetDirection);
            return;
        }

        boolean canMoveDownLeft = isCellEmpty(Direction.DOWN_LEFT);
        boolean canMoveDownRight = isCellEmpty(Direction.DOWN_RIGHT);

        if (canMoveDownLeft && canMoveDownRight) {
            targetDirection = RandomUtils.selectRandom(Direction.DOWN_LEFT, Direction.DOWN_RIGHT);
        }
        else if (canMoveDownLeft) {
            targetDirection = Direction.DOWN_LEFT;
        }
        else if (canMoveDownRight) {
            targetDirection = Direction.DOWN_RIGHT;
        }

        move(targetDirection, fallRate);
    }
}
