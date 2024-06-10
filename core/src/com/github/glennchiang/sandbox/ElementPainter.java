package com.github.glennchiang.sandbox;

import com.github.glennchiang.sandbox.elements.ElementType;

// Adds elements to the world grid via direct user interaction
// This class should not be used for dynamic reactions between elements
public class ElementPainter
{
    private ElementType activeElement = ElementType.SAND; // Default element is sand

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
