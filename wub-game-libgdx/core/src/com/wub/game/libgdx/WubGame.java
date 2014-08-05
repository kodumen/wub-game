package com.wub.game.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WubGame extends ApplicationAdapter {
    private final int WIDTH = 480, HEIGHT = 800;

    private SpriteBatch batch;
    private Camera camera;
    private Viewport viewport;

    // Images
    private Texture bg480p;

    //Stages
    private Stage mainMenu;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);

        // LOAD ASSETS
        bg480p = new Texture("bg480x800.png");


        // SET UP STAGES AND ACTORS
        GameActor bgDisc = new GameActor();
        bgDisc.setTexture(bg480p);
        bgDisc.setPosition(0, 0);

        // mainMenu Stage Setup
        mainMenu = new Stage(viewport, batch);
        mainMenu.addActor(bgDisc);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainMenu.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);     // Remember to update the viewport!
    }

    @Override
    public void dispose() {
        mainMenu.dispose();
    }
}
