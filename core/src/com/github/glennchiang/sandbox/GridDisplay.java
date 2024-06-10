package com.github.glennchiang.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.github.glennchiang.sandbox.elements.Element;

public class GridDisplay {
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;

    // Data from this grid will be displayed by GridDisplay
    private final Grid grid;

    // Shape representing the grid
    private final Rectangle gridFrame;
    // Shapes representing the cells of the grid
    private final Rectangle[][] cells;

    private final ElementPainter elementPainter = new ElementPainter();

    // GridDisplay is initialized by passing its position (bottom-left corner)
    // and the abstract grid of elements to display
    public GridDisplay(int x, int y, Grid grid) {
        this.grid = grid;

        // Create rectangle representing grid boundaries
        gridFrame = new Rectangle();
        gridFrame.x = x;
        gridFrame.y = y;
        gridFrame.width = WIDTH;
        gridFrame.height = HEIGHT;

        int cellWidth = WIDTH / grid.numCols;
        int cellHeight = HEIGHT / grid.numRows;

        // Create rectangles representing cells
        cells = new Rectangle[grid.numRows][grid.numCols];
        for (int row = 0; row < grid.numRows; row++) {
            for (int col = 0; col < grid.numCols; col++) {
                Rectangle cell = new Rectangle();
                cell.x = gridFrame.x + col * cellWidth;
                cell.y = gridFrame.y + HEIGHT - (row + 1) * cellHeight;
                cell.width = cellWidth;
                cell.height = cellHeight;
                cells[row][col] = cell;
            }
        }
    }

    public void render(ShapeRenderer shapeRenderer) {
        // Draw the grid frame
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(gridFrame.x, gridFrame.y, gridFrame.width, gridFrame.height);
        shapeRenderer.end();

        // Draw the cells within the grid, from top to bottom
        for (int row = 0; row < grid.numRows; row++) {
            for (int col = 0; col < grid.numCols; col++) {
                Element element = grid.elementAt(row, col);
                // Skip over empty cells
                if (element == null) {
                    continue;
                }

                // Draw cell shape based on corresponding rectangle's dimensions and position
                // Fill color is based on the contained element
                Rectangle cell = cells[row][col];
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(element.getColor());
                shapeRenderer.rect(cell.x, cell.y, cell.width, cell.height);
                shapeRenderer.end();
            }
        }
    }

    public void handleTouch(Vector2 touchPos) {
        // Check if touchPos is in grid frame. If not, do nothing.
        if (!gridFrame.contains(touchPos)) return;
        // Find the cell that was clicked and paint the active element at that cell
        for (int row = 0; row < grid.numRows; row++) {
            for (int col = 0; col < grid.numCols; col++) {
                Rectangle cell = cells[row][col];
                if (cell.contains(touchPos)) {
                    elementPainter.paintCell(grid, row, col);
                }
            }
        }
    }

}
