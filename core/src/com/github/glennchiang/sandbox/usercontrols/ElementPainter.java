package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.github.glennchiang.sandbox.GridDisplay;
import com.github.glennchiang.sandbox.elements.ElementType;

// Adds elements to the world grid via direct user interaction
// This class should not be used for dynamic reactions between elements
public class ElementPainter
{
    private final GridDisplay gridDisplay;
    private Vector3 cursorPos = new Vector3();
    private final Circle brushArea;
    private final int minBrushRadius = 2;
    private final int maxBrushRadius = 64;
    private int brushRadius = 32; // Radius of brush cursor in pixels

    private ElementType activeElement = ElementType.SAND;
    private boolean brushActive = false;

    public ElementPainter(GridDisplay gridDisplay) {
        this.gridDisplay = gridDisplay;

        // Initialize the circle representing the "hit box" area of the brush
        brushArea = new Circle();
        brushArea.x = 0;
        brushArea.y = 0;
        brushArea.radius = brushRadius;
    }

    // Runs every frame to poll for inputs
    public void render(ShapeRenderer shapeRenderer) {
        if (!brushActive) return;
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(activeElement.color);
        shapeRenderer.circle(cursorPos.x, cursorPos.y, brushRadius);
        shapeRenderer.end();

        if (Gdx.input.isTouched()) {
            paint();
        }
    }

    public void updateCursorPos(Vector3 pos) {
        cursorPos = pos;
        brushArea.x = cursorPos.x;
        brushArea.y = cursorPos.y;

        // If cursor is over grid frame, switch to brush cursor
        brushActive = gridDisplay.gridFrame.contains(cursorPos.x, cursorPos.y);
    }

    public void paint() {
        gridDisplay.fillAreaWithElement(brushArea, activeElement);
    }

    public void changeBrushRadius(float amount) {
        // Change brush radius while ensuring it remains within allowed range
        brushRadius = (int) MathUtils.clamp(brushRadius + amount, minBrushRadius, maxBrushRadius);
        brushArea.radius = brushRadius;
    }

    public ElementType[] getElementTypes() {
        return ElementType.values();
    }

    public void setActiveElement(ElementType elementType) {
        this.activeElement = elementType;
    }
}
