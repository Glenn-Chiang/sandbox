package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.Fire;
import com.github.glennchiang.sandbox.elements.fluids.gas.Smoke;
import com.github.glennchiang.sandbox.elements.fluids.granules.Dirt;
import com.github.glennchiang.sandbox.elements.fluids.granules.Sand;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Acid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Mud;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Water;
import com.github.glennchiang.sandbox.elements.solids.Stone;

public enum ElementType {
    EMPTY(Color.WHITE) {
      @Override
      public Element createInstance(Grid grid) {
          return null;
      }
    },
    STONE(Color.SLATE) {
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
    DIRT(Color.BROWN) {
        @Override
        public Dirt createInstance(Grid grid) { return new Dirt(grid); }
    },
    WATER(Color.SKY) {
        @Override
        public Water createInstance(Grid grid) {return new Water(grid); }
    },
    MUD(Color.valueOf("6E2C00")) {
        @Override
        public Mud createInstance(Grid grid) { return new Mud(grid); }
    },
    ACID(Color.CHARTREUSE) {
        @Override
        public Acid createInstance(Grid grid) { return new Acid(grid); }
    },
    FIRE(Color.ORANGE) {
        @Override
        public Fire createInstance(Grid grid) { return new Fire(grid); }
    },
    SMOKE(Color.DARK_GRAY) {
        @Override
        public Smoke createInstance(Grid grid) { return new Smoke(grid); }
    }
    ;

    public final Color color;

    ElementType(Color color) {
        this.color = color;
    }

    public abstract Element createInstance(Grid grid);
}
