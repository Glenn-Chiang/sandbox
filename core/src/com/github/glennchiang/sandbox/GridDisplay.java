package com.github.glennchiang.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.glennchiang.sandbox.elements.Element;

public class GridDisplay {
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;
    private final int x;
    private final int y;
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    // Data from this grid will be displayed by GridDisplay
    private final Grid grid;

    // GridDisplay is initialized by passing its position (bottom-left corner)
    // and the abstract grid of elements to display
    public GridDisplay(int x, int y, Grid grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public void render() {
        // Draw the grid frame
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x, y, WIDTH, HEIGHT);
        shapeRenderer.end();

        int cellWidth = WIDTH / grid.numCols;
        int cellHeight = HEIGHT / grid.numRows;

        // Draw the cells within the grid, from top to bottom
        for (int row = 0; row < grid.numRows; row++) {
            for (int col = 0; col < grid.numCols; col++) {
                Element element = grid.elementAt(row, col);
                // Skip over empty cells
                if (element == null) {
                    continue;
                }
                // Draw cell based on its contained element
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(element.getColor());
                shapeRenderer.rect(x + col * cellWidth, y + HEIGHT - (row + 1) * cellHeight, cellWidth, cellHeight);
                shapeRenderer.end();
            }
        }
    }
}
