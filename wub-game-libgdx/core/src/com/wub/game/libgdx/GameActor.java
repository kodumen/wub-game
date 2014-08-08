package com.wub.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by R on 8/5/2014.
 */
public class GameActor extends Actor {
    private Texture texture;
    private Polygon collider = null;

    public void setTexture(Texture texture) {
        this.texture = texture;
        this.setSize(texture.getWidth(), texture.getHeight());
    }

    public void setCollider(float[] vertices) {
        collider = new Polygon(vertices);
        collider.setPosition(this.getX(), this.getY());
        collider.setOrigin(this.getOriginX(), this.getOriginY());
    }

    public float[] getCollider() {
        return collider.getTransformedVertices();
    }

    public boolean hasCollider() { return collider != null; }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), this.getOriginX(), this.getOriginY(),
                this.getWidth(), this.getHeight(), this.getScaleX(), this.getScaleY(), this.getRotation(),
                0, 0, texture.getWidth(), texture.getHeight(), false, false);
    }

    /***
     * To be called everytime position, origin, or rotation of the GameActor is changed.
     */
    public void updateCollider() {
        collider.setPosition(this.getX(), this.getY());
        collider.setOrigin(this.getOriginX(), this.getOriginY());
        collider.setRotation(this.getRotation());
    }
}
