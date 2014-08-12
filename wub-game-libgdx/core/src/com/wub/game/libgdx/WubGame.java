package com.wub.game.libgdx;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.wub.game.libgdx.Behavior.Collider;
import com.wub.game.libgdx.Behavior.PieAction;
import com.wub.game.libgdx.Behavior.ShaftAction;

public class WubGame extends ApplicationAdapter {
    private SpriteBatch spritebatch;
    private ShapeRenderer shapeRenderer;
    private Camera camera;
    private Viewport viewport;

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
        shapeRenderer = new ShapeRenderer();
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

        playScreen = new GameObject("PlayScreen");
        GameObject bg00 = new GameObject();
        GameObject bg01 = new GameObject();
        GameObject shaft = new GameObject("Shaft");
        GameObject pie = new GameObject("Pie");
        GameObject pieSlice = new GameObject("PieSlice");

        // Add objects to scene
        playScreen.add(bg00);
        playScreen.add(bg01);
        playScreen.add(shaft);
        playScreen.add(pie, 1);

        // Add behaviors/components to objects
        shaft.addComponent(new Collider());
        shaft.addComponent(new ShaftAction());
        pieSlice.addComponent(new Collider());
        pie.addComponent(new PieAction());

        // Set behavior/component properties
        bg00.render.setTexture(BG480p);
        bg01.render.setTexture(DSCOUT);
        bg01.transform.setX(WIDTH / 2 - bg01.transform.getWidth() / 2);
        bg01.transform.setY(HEIGHT / 2 - bg01.transform.getHeight() / 2);
        shaft.render.setTexture(SHAFT);
        shaft.transform.setX(WIDTH / 2 - shaft.transform.getWidth() / 2);
        shaft.transform.setY(HEIGHT / 2);
        shaft.transform.setOrigin(shaft.transform.getWidth() / 2, 0f);
        ((Collider)shaft.getComponent("Collider")).setPolygon();
        ShaftAction shaftAction = (ShaftAction)shaft.getComponent("ShaftAction");
        shaftAction.setSpeed(25f);
        shaftAction.setInitDirection(ShaftAction.CLKWISE);
        pieSlice.render.setTexture(PIE_0);
        pieSlice.transform.setX(WIDTH / 2 - pieSlice.transform.getWidth() / 2);
        pieSlice.transform.setY(HEIGHT / 2);
        pieSlice.transform.setOrigin(pieSlice.transform.getWidth() / 2, 0f);
        pieSlice.transform.setRotation(11.25f);
        ((Collider)pieSlice.getComponent("Collider")).setPolygon(new float[]{
                0f, pieSlice.transform.getHeight() - 12f,
                0f, pieSlice.transform.getHeight(),
                pieSlice.transform.getWidth(), pieSlice.transform.getHeight(),
                pieSlice.transform.getWidth(), pieSlice.transform.getHeight() - 12f
        });
        PieAction pieAction = (PieAction)pie.getComponent("PieAction");
        pieAction.setPieSlice(pieSlice);
        pieAction.create();
    }

    @Override
    public void render() {
        camera.update();
        playScreen.update(Gdx.graphics.getDeltaTime());

        // Draw Render Textures
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.setProjectionMatrix(camera.combined);
        spritebatch.begin();
        draw(playScreen, spritebatch);
        spritebatch.end();

        // Draw Collider Polygons
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0, 1, 1, 1);
        drawColliders(playScreen, shapeRenderer);
        shapeRenderer.end();

        // HOLY SHIT AN FPS COUNTER!
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + "FPS");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    public void draw(GameObject gameObject, SpriteBatch batch) {
        if (gameObject.render.hasTexture()) {
            batch.draw(gameObject.render.getTexture(), gameObject.transform.getX(), gameObject.transform.getY(),
                    gameObject.transform.getOriginX(), gameObject.transform.getOriginY(),
                    gameObject.transform.getWidth(), gameObject.transform.getHeight(),
                    gameObject.transform.getScaleX(), gameObject.transform.getScaleY(),
                    gameObject.transform.getRotation(),
                    0, 0, (int) gameObject.transform.getWidth(), (int) gameObject.transform.getHeight(),
                    false, false);
        }

        if (gameObject.hasChildren()) {
            for(int i = 0; i < gameObject.getChildren().size; i++)
                draw(gameObject.getChild(i), batch);
        }
    }

    public void drawColliders(GameObject gameObject, ShapeRenderer shapeRenderer) {
        if (gameObject.hasComponent("Collider")) {
            shapeRenderer.polygon(((Collider)gameObject.getComponent("Collider")).getCollider());
        }

        if (gameObject.hasChildren()) {
            for(int i = 0; i < gameObject.getChildren().size; i++)
                drawColliders(gameObject.getChild(i), shapeRenderer);
        }
    }
}
