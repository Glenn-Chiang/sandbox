package com.github.glennchiang.sandbox;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GridDisplay {
    public final static int WIDTH = 600;
    public final static int HEIGHT = 600;
    private final int x;
    private final int y;
    private final ShapeRenderer gridFrame = new ShapeRenderer();

    // GridDisplay is initialized by passing its bottom-left corner position to the constructor
    public GridDisplay(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render() {
        gridFrame.begin(ShapeRenderer.ShapeType.Line);
        gridFrame.setColor(Color.WHITE);
        gridFrame.rect(x, y, WIDTH, HEIGHT);
        gridFrame.end();
    }
}
