package com.wub.game.libgdx.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.wub.game.libgdx.WubGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "wub wub";
        config.resizable = false;
        config.width = 360;
        config.height = 600;
		new LwjglApplication(new WubGame(), config);
	}
}
