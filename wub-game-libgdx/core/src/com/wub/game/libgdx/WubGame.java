package com.wub.game.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WubGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture bg;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new Texture("bg360x600.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(bg, 0, 0);
		batch.end();
	}

    @Override
    public void resize(int width, int height) {

    }
}
