package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class InputManager implements InputProcessor {
    public final InputMultiplexer multiplexer = new InputMultiplexer();
    private final ElementPainter elementPainter;

    public InputManager(ElementPainter elementPainter, Stage stage) {
        this.elementPainter = elementPainter;
        multiplexer.addProcessor(this);
        multiplexer.addProcessor(stage);
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        elementPainter.changeBrushRadius(amountY);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
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
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
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
