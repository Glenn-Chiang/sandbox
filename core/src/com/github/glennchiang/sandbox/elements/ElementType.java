package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.moveables.granules.Sand;
import com.github.glennchiang.sandbox.elements.moveables.liquids.Water;
import com.github.glennchiang.sandbox.elements.solids.Stone;

public enum ElementType {
    EMPTY(Color.WHITE) {
      @Override
      public Element createInstance(Grid grid) {
          return null;
      }
    },
    STONE(Color.GRAY) {
        @Override
        public Stone createInstance(Grid grid) {
            return new Stone(grid);
        }
    },
    SAND(Color.valueOf("F7DC6F")) {
        @Override
        public Sand createInstance(Grid grid) {
            return new Sand(grid);
        }
    },
    WATER(Color.SKY) {
        @Override
        public Water createInstance(Grid grid) {return new Water(grid); }
    };

    public final Color color;

    ElementType(Color color) {
        this.color = color;
    }

    public abstract Element createInstance(Grid grid);
}
