package com.yan.waterworld;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "WaterWorldGameGDX";
		cfg.useGL20 = false;
		cfg.width = 320;
		cfg.height = 480;
		
		cfg.resizable = false;
		
		new LwjglApplication(new WaterWorldGame(), cfg);
	}
}
