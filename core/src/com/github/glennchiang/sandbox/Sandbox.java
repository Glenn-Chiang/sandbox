package com.github.glennchiang.sandbox;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Sandbox extends ApplicationAdapter {
    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 640;
    private FitViewport viewport;
    private OrthographicCamera camera;
    final Vector3 touchPos = new Vector3(); // Position of last touch/click interaction

    private final int GRID_ROWS = 150;
    private final int GRID_COLS = 150;
    private Grid worldGrid; // The abstract grid containing the elements present in the world

    private final int GRID_WIDTH = 600;
    private final int GRID_HEIGHT = 600;
    private GridDisplay gridDisplay; // The visual representation of the abstract grid

    private ShapeRenderer shapeRenderer; // Used to draw all shapes

    @Override
    public void create() {
        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        worldGrid = new Grid(GRID_ROWS, GRID_COLS);
        gridDisplay = new GridDisplay((SCREEN_WIDTH - GRID_WIDTH) / 2, (SCREEN_HEIGHT - GRID_HEIGHT) / 2, GRID_WIDTH, GRID_HEIGHT, worldGrid);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render() {
        // Fill screen with black background
        ScreenUtils.clear(0, 0, 0, 1);
        camera.update();

        gridDisplay.render(shapeRenderer);
        worldGrid.update();

        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos); // Translate point from screen space to world space
            gridDisplay.handleTouch(new Vector2(touchPos.x, touchPos.y));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
