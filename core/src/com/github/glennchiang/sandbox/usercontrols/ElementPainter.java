package com.github.glennchiang.sandbox.usercontrols;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.GridDisplay;
import com.github.glennchiang.sandbox.elements.Element;
import com.github.glennchiang.sandbox.elements.ElementType;

// Adds elements to the world grid via direct user interaction
// This class should not be used for dynamic reactions between elements
public class ElementPainter
{
    private ElementType activeElement = ElementType.SAND;

    public ElementType[] getElementTypes() {
        return ElementType.values();
    }

    public void setActiveElement(ElementType elementType) {
        this.activeElement = elementType;
    }

    // Create an instance of the active element and place it at the given position on the grid
    public void paintCell(Grid grid, int row, int col) {
        grid.setElement(row, col, activeElement.createInstance(grid));
    }

}
