package com.github.glennchiang.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Sandbox extends ApplicationAdapter {
    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 640;
    private FitViewport viewport;
    private OrthographicCamera camera;
    private final int gridRows = 60;
    private final int gridCols = 60;
    private Grid grid;
    private GridDisplay gridDisplay;

    @Override
    public void create() {
        // Set up camera
        camera = new OrthographicCamera();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        grid = new Grid(gridRows, gridCols);
        gridDisplay = new GridDisplay((SCREEN_WIDTH - GridDisplay.WIDTH) / 2, (SCREEN_HEIGHT - GridDisplay.HEIGHT) / 2, grid);
    }

    @Override
    public void render() {
        // Fill screen with black background
        ScreenUtils.clear(0, 0, 0, 1);

        gridDisplay.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {

    }
}
