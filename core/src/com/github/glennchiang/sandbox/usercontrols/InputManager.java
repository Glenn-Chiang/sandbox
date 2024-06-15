package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputManager implements InputProcessor {
    public final InputMultiplexer multiplexer = new InputMultiplexer();

    private final Camera camera;
    private Vector3 cursorPos = new Vector3();
    private final ElementPainter elementPainter;

    public InputManager(Camera camera, ElementPainter elementPainter, Stage stage) {
        this.camera = camera;
        this.elementPainter = elementPainter;
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        camera.unproject(cursorPos.set(screenX, screenY, 0));
        elementPainter.updateCursorPos(cursorPos);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        camera.unproject(cursorPos.set(screenX, screenY, 0));
        elementPainter.updateCursorPos(cursorPos);
        elementPainter.paint();
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        elementPainter.changeBrushRadius(amountY);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        elementPainter.paint();
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }
}
