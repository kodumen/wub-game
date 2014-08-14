package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.math.Polygon;
import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.GameObject;

/**
 * Created by R on 8/11/2014.
 */
public class Collider extends GameComponent {
    private Polygon polygon;

//    public Collider() {
//        this.polygon = new Polygon();
//    }
//
//    public Collider(float[] vertices) {
//        super();
//        this.polygon = new Polygon(vertices);
//    }

//    @Override
//    public void setGameObject(GameObject gameObject) {
//        super.setGameObject(gameObject);
//        update(0);
//    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(float[] vertices) {
        this.polygon = null;
        this.polygon = new Polygon(vertices);
    }

    public void setPolygon() {
        float width = gameObject.transform.getWidth();
        float height = gameObject.transform.getHeight();
        setPolygon(new float[]{
            0f, 0f,
            0f, height,
            width, height,
            width, 0f
        });
    }

    public float[] getCollider() {
        return polygon.getTransformedVertices();
    }

    @Override
    public void update(float deltaTime) {
        polygon.setPosition(gameObject.transform.getX(), gameObject.transform.getY());
        polygon.setOrigin(gameObject.transform.getOriginX(), gameObject.transform.getOriginY());
        polygon.setScale(gameObject.transform.getScaleX(), gameObject.transform.getScaleY());
        polygon.setRotation(gameObject.transform.getRotation());
    }

    @Override
    public void create() {
        this.polygon = new Polygon();
        update(0);
    }
}
