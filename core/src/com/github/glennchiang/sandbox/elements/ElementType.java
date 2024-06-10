package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Grid;

public enum ElementType {
    STONE(Stone.class) {
        @Override
        public Stone createInstance() {
            return new Stone();
        }
    },
    SAND(Sand.class) {
        @Override
        public Sand createInstance() {
            return new Sand();
        }
    };

    public final Class<? extends Element> clazz;

    ElementType(Class<? extends Element> elementClass) {
        this.clazz = elementClass;
    }

    // Create an instance of this element type at the given position on the grid
    public abstract Element createInstance();
}
