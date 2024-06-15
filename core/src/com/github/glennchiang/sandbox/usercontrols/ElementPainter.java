package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.GridDisplay;
import com.github.glennchiang.sandbox.elements.ElementType;

// Adds elements to the world grid via direct user interaction
// This class should not be used for dynamic reactions between elements
public class ElementPainter
{
    private final GridDisplay gridDisplay;
    private final Circle brushArea;
    private final Cursor brushCursor;
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

        brushCursor = createBrushCursor();
        switchToBrushCursor();
    }

    // Runs every frame to poll for inputs
    public void render(Vector2 cursorPos) {
        brushArea.x = cursorPos.x;
        brushArea.y = cursorPos.y;

        // If cursor is over grid frame, switch to brush cursor
        if (gridDisplay.gridFrame.contains(cursorPos)) {
            if (!brushActive) {
                switchToBrushCursor();
            }
            brushActive = true;
            if (Gdx.input.isTouched()) {
                gridDisplay.fillAreaWithElement(brushArea, activeElement);
            }
        // If cursor is outside grid frame and brush was still active, switch to default cursor
        } else if (brushActive) {
            switchToDefaultCursor();
            brushActive = false;
        }
    }

    private void switchToBrushCursor() {
        Gdx.graphics.setCursor(brushCursor);
    }

    private void switchToDefaultCursor() {
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
    }

    // This cursor will be used when the brush is active, i.e. when the cursor is over the grid
    private Cursor createBrushCursor() {
        // Create cursor
        Pixmap pixmap = new Pixmap(64, 64, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.drawCircle(brushRadius, brushRadius, brushRadius - 2);
        // Set cursor hotspot to center
        int xHotspot = pixmap.getWidth() / 2;
        int yHotspot = pixmap.getHeight() / 2;
        Cursor cursor = Gdx.graphics.newCursor(pixmap, xHotspot, yHotspot);
        pixmap.dispose();
        return cursor;
    }

    public ElementType[] getElementTypes() {
        return ElementType.values();
    }

    public void setActiveElement(ElementType elementType) {
        this.activeElement = elementType;
    }
}
