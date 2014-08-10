package com.wub.game.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WubGame extends ApplicationAdapter {
    SpriteBatch spritebatch;
    Camera camera;
    Viewport viewport;

    public static final int WIDTH = 480, HEIGHT = 800;

    // Textures
    public static Texture BG480p;
    public static Texture DSCOUT;
    public static Texture SHAFT;
    public static Texture PIE_0;
    public static Texture PIE_1;
    public static Texture PIE_2;
    public static Texture PIE_3;

    // Game Objects
    private GameObject playScreen;


    @Override
    public void create() {
        spritebatch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);

        // LOAD TEXTURES
        BG480p = new Texture("bg480x800.png");
        DSCOUT = new Texture("disc_outer.png");
        SHAFT = new Texture("shaft.png");
        PIE_0 = new Texture("pie_green.png");
        PIE_1 = new Texture("pie_yellow.png");
        PIE_2 = new Texture("pie_purple.png");
        PIE_3 = new Texture("pie_red.png");

        // CREATE GAME OBJECTS
        // Play Screen
        playScreen = new GameObject("PlayScreen");
        GameObject bg00 = new GameObject();
        bg00.texture = BG480p;

        playScreen.add(bg00);

    }

    @Override
    public void render() {
        playScreen.update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.begin();
        draw(playScreen, spritebatch);
        spritebatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.update();
        viewport.update(width, height);
    }

    public void draw(GameObject gameObject, SpriteBatch batch) {
        if(gameObject.hasTexture()) {
            batch.draw(gameObject.texture, gameObject.x, gameObject.y, gameObject.originX, gameObject.originY,
                    gameObject.getWidth(), gameObject.getHeight(), 1f, 1f, gameObject.rotation,
                    0, 0, (int) gameObject.getWidth(), (int) gameObject.getHeight(), false, false);
        }

        if (gameObject.hasChildren()) {
            for(int i = 0; i < gameObject.getChildren().size; i++)
                draw(gameObject.getChild(i), batch);
        }
    }
}
