package com.github.glennchiang.sandbox.elements.moveables;

import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class MovableElement extends Element {
    private final int fallRate; // Number of cells by which the element will move down per frame

    public MovableElement(Grid grid) {
        super(grid);
        fallRate = getFallRate();
    }

    protected abstract int getFallRate();

    // Distance: number of cells to move in the given direction
    // If the element is unable to move at least 1 cell in the given direction, return false
    protected final boolean move(Direction dir, int distance) {
        boolean moved = false;
        for (int i = 0; i < distance; i++) {
            // Try to move this element by 1 cell in the given direction
            // If unable to move, stop trying to move further
            if (step(dir)) {
                moved = true;
            } else {
                break;
            }
        }
        return moved;
    }

    // Move by 1 cell in the given direction if the move is possible
    private boolean step(Direction dir) {
        CellPosition targetPos = getCellPosition(dir);
        if (!grid.inBounds(targetPos)) return false;

        // Move to target position if it is empty
        if (isCellEmpty(dir)) {
            grid.moveElement(row, col, targetPos.row, targetPos.col);
            return true;
        }
        // Check if there are other ways to move to target position, and move if possible
        // e.g. sink or swap with element at target position
        return tryStep(dir);
    }

    // Subclasses can implement other conditions and ways to move
    protected abstract boolean tryStep(Direction dir);


    protected interface Move {
        boolean doMove();
    }

    // Given a list of moves, randomly select a move to execute
    // If selected move fails, select another move until all moves are tried
    protected final boolean randomMove(List<Move> moves) {
        Collections.shuffle(moves, new Random());
        for (Move move: moves) {
            if (move.doMove()) {
                return true;
            }
        }
        return false;
    }

    protected final void swap(Direction dir) {
        grid.swapElements(row, col, row + dir.y, col + dir.x);
    }

    // Check whether this element will sink in the given element
    protected abstract boolean sinksIn(Element element);

    // Try to fall in the given direction
    protected final boolean fall(Direction dir) {
        return move(dir, fallRate);
    }
}
