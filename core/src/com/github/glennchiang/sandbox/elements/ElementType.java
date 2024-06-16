package com.github.glennchiang.sandbox.elements;

import com.badlogic.gdx.graphics.Color;
import com.github.glennchiang.sandbox.Grid;
import com.github.glennchiang.sandbox.elements.fluids.Fire;
import com.github.glennchiang.sandbox.elements.fluids.gas.Smoke;
import com.github.glennchiang.sandbox.elements.fluids.gas.Steam;
import com.github.glennchiang.sandbox.elements.fluids.granules.Dirt;
import com.github.glennchiang.sandbox.elements.fluids.granules.Sand;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Acid;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Mud;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Oil;
import com.github.glennchiang.sandbox.elements.fluids.liquids.Water;
import com.github.glennchiang.sandbox.elements.solids.Stone;
import com.github.glennchiang.sandbox.elements.solids.Wood;

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
    WOOD(Color.valueOf("#7E5109")) {
      @Override
      public Wood createInstance(Grid grid) { return new Wood(grid); }
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
    MUD(Color.valueOf("6E2C00")) {
        @Override
        public Mud createInstance(Grid grid) { return new Mud(grid); }
    },
    WATER(Color.SKY) {
        @Override
        public Water createInstance(Grid grid) {return new Water(grid); }
    },
    ACID(Color.CHARTREUSE) {
        @Override
        public Acid createInstance(Grid grid) { return new Acid(grid); }
    },
    OIL(Color.GOLDENROD) {
      @Override
      public Oil createInstance(Grid grid) {return new Oil(grid);}
    },
    FIRE(Color.ORANGE) {
        @Override
        public Fire createInstance(Grid grid) { return new Fire(grid); }
    },
    SMOKE(Color.DARK_GRAY) {
        @Override
        public Smoke createInstance(Grid grid) { return new Smoke(grid); }
    },
    STEAM(Color.LIGHT_GRAY) {
        @Override
        public Steam createInstance(Grid grid) { return new Steam(grid); }
    }
    ;

    public final Color color;

    ElementType(Color color) {
        this.color = color;
    }

    public abstract Element createInstance(Grid grid);
}
