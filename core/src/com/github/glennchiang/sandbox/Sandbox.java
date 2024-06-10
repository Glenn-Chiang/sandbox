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
    private final int gridRows = 60;
    private final int gridCols = 60;
    private Grid worldGrid; // The abstract grid containing the elements present in the world
    private GridDisplay gridDisplay; // The visual representation of the abstract grid
    private ShapeRenderer shapeRenderer;
    @Override
    public void create() {
        // Set up camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
//        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);
        worldGrid = new Grid(gridRows, gridCols);
        gridDisplay = new GridDisplay((SCREEN_WIDTH - GridDisplay.WIDTH) / 2, (SCREEN_HEIGHT - GridDisplay.HEIGHT) / 2, worldGrid);
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

//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }


    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
