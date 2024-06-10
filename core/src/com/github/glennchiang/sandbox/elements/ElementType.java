package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Grid;

public enum ElementType {
    STONE(Stone.class) {
        @Override
        public Stone createInstance(Grid grid) {
            return new Stone(grid);
        }
    },
    SAND(Sand.class) {
        @Override
        public Sand createInstance(Grid grid) {
            return new Sand(grid);
        }
    };

    public final Class<? extends Element> clazz;

    ElementType(Class<? extends Element> elementClass) {
        this.clazz = elementClass;
    }

    public abstract Element createInstance(Grid grid);
}
