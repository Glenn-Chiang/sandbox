package com.github.glennchiang.sandbox.utils;

public class RandomUtils {
//    public static <T> T selectRandom(T[] items) {
//        int randomIndex = (int) (Math.random() * items.length);
//        return items[randomIndex];
//    }

    public static <T> T selectRandom(T... items) {
        int randomIndex = (int) (Math.random() * items.length);
        return items[randomIndex];
    }
}
