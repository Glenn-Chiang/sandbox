package com.github.glennchiang.sandbox.elements.fluids;

import com.github.glennchiang.sandbox.CellPosition;
import com.github.glennchiang.sandbox.Direction;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.Element;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Fluid extends Element {
    // Number of cells by which the element will move vertically per frame
    private final int fallRate;

    // Number of cells by which the element will move horizontally per frame
    private final int flowRate;

    // 2D list of moves ordered by priority
    // The inner lists are arranged in order of priority from first to last
    // Within each inner list, moves are of equal priority and will be randomly selected
    // If no moves in the inner list succeed, continue to the next inner list
    private final List<List<Move>> moves;
    protected abstract List<List<Move>> getMoves();

    public Fluid(Grid grid, int durability, boolean flammable, int fallRate, int flowRate) {
        super(grid, durability, flammable);
        moves = getMoves();
        this.fallRate = fallRate;
        this.flowRate = flowRate;
    }

    @Override
    protected void update() {
        iterateMoves();
    }
    protected interface Move {
        boolean doMove();
    }

    // Try every possible move in order of priority
    private void iterateMoves() {
        for (List<Move> moveSet: moves) {
            if (randomMove(moveSet)) {
                return;
            }
        }
    }

    // Randomly select a move to execute
    // If selected move fails, select another move until all moves are tried
    private boolean randomMove(List<Move> moves) {
        Collections.shuffle(moves, new Random());
        for (Move move: moves) {
            if (move.doMove()) {
                return true;
            }
        }
        return false;
    }

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
    // Offset indicates how many steps have already been made in the given direction
    private boolean step(Direction dir) {
        CellPosition targetPos = getCellPosition(dir);
        if (!grid.inBounds(targetPos)) return false;

        // Move to target position if it is empty
        if (isCellEmpty(dir)) {
            grid.moveElement(row, col, targetPos.row, targetPos.col);
            row = targetPos.row;
            col = targetPos.col;
            return true;
        }

        // Sink in element at target position, if possible
        if (sinksIn(getNeighbor(dir))) {
            swap(dir);
            return true;
        }
        return false;
    }

    // Try to fall in the given direction
    protected final boolean fall(Direction dir) {
        return move(dir, fallRate );

    }

    protected final boolean flow(Direction dir) {
        return move(dir, flowRate);
    }

    protected final void swap(Direction dir) {
        grid.swapElements(row, col, row + dir.y, col + dir.x);

    }

    // Check whether this element will sink in the given element
    protected abstract boolean sinksIn(Element element);
}
