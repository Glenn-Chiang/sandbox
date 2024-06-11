package com.github.glennchiang.sandbox.elements;

import com.github.glennchiang.sandbox.Grid;

public enum ElementType {
    EMPTY() {
      @Override
      public Element createInstance(Grid grid) {
          return null;
      }
    },
    STONE() {
        @Override
        public Stone createInstance(Grid grid) {
            return new Stone(grid);
        }
    },
    SAND() {
        @Override
        public Sand createInstance(Grid grid) {
            return new Sand(grid);
        }
    },
    WATER() {
        @Override
        public Water createInstance(Grid grid) {return new Water(grid); }
    };

    public abstract Element createInstance(Grid grid);
}
