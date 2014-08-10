package com.wub.game.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wub.game.libgdx.actions.Spin;

public class WubGame extends ApplicationAdapter {
    private final int WIDTH = 480, HEIGHT = 800;

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private Viewport viewport;

    // Images
    private Texture bg480p;
    private Texture disc480p;
    private Texture shaftTex;
    public Texture pieGreen;

    //Stages
    private Stage playStage;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(WIDTH, HEIGHT, camera);

        // LOAD ASSETS
        bg480p = new Texture("bg480x800.png");
        disc480p = new Texture("disc_outer.png");
        shaftTex = new Texture("shaft.png");
        pieGreen = new Texture("pie_green.png");

        // SET UP STAGES AND ACTORS
        GameActor bgDisc = new GameActor();
        bgDisc.setTexture(bg480p);
        bgDisc.setPosition(0, 0);

        GameActor dscOuter = new GameActor();
        dscOuter.setTexture(disc480p);
        dscOuter.setCenterPosition(WIDTH / 2, HEIGHT / 2);

        // SHAFT!
        GameActor shaft = new GameActor();
        shaft.setName("shaft");
        shaft.setTexture(shaftTex);
        shaft.setX((WIDTH / 2) - (shaft.getWidth() / 2));
        shaft.setY(HEIGHT / 2);
        shaft.setOrigin(shaft.getWidth() / 2, 0);
        shaft.setCollider(new float[]{
                0f, shaft.getHeight() - 64f,
                0f, shaft.getHeight(),
                shaft.getWidth(), shaft.getHeight(),
                shaft.getWidth(), shaft.getHeight() - 64f
        });
        shaft.addAction(new Spin(25.5f, Spin.CLOCKWISE));

        // Basic Pie
        GameActor basicPie = new GameActor();
        basicPie.setName("pie");
        basicPie.setTexture(pieGreen);     // This is temporary
        basicPie.setX((WIDTH / 2) - (basicPie.getWidth() / 2));
        basicPie.setY(HEIGHT / 2);
        basicPie.setOrigin(basicPie.getWidth() / 2, 0);
        basicPie.setCollider(new float[]{
                0f, basicPie.getHeight() - 24f,
                0f, basicPie.getHeight(),
                basicPie.getWidth(), basicPie.getHeight(),
                basicPie.getWidth(), basicPie.getHeight() - 24f
        });

        // playStage Stage Setup
        playStage = new Stage(viewport, batch);
        playStage.addActor(bgDisc);
        playStage.addActor(dscOuter);
        playStage.addActor(shaft);
        playStage.addActor(basicPie);

        // Set z index only after adding actor to stage
        bgDisc.setZIndex(0);
        basicPie.setZIndex(1);
        dscOuter.setZIndex(2);
        shaft.setZIndex(3);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        playStage.act();
        playStage.draw();
        drawColliders(playStage, shapeRenderer);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);     // Remember to update the viewport!
    }

    @Override
    public void dispose() {
        playStage.dispose();
    }

    public void drawColliders(Stage stage, ShapeRenderer shapeRenderer) {
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined); // This is just standard procedure. I don't really know what's happening.

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 1, 1);

        Array<Actor> actors = stage.getActors();
        for(int i = 0; i < actors.size; i++) {
            GameActor actor = (GameActor)actors.get(i);
            if(!actor.hasCollider()) continue;
            shapeRenderer.polygon(actor.getCollider());
        }

        shapeRenderer.end();
    }
}
