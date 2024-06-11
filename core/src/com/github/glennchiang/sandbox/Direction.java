package com.github.glennchiang.sandbox;

public enum Direction {
    TOP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOP_LEFT(-1, -1),
    TOP_RIGHT(1, - 1),
    DOWN_LEFT(-1, 1),
    DOWN_RIGHT(1, 1);

    public final int x;
    public final int y;
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
