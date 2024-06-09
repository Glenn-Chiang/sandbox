package com.github.glennchiang.sandbox;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.github.glennchiang.sandbox.Sandbox;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Sandbox");
		config.setForegroundFPS(60);
		config.setWindowedMode(Sandbox.SCREEN_WIDTH, Sandbox.SCREEN_HEIGHT);
//		config.useVsync(true);
		new Lwjgl3Application(new Sandbox(), config);
	}
}
