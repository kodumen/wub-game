package com.wub.game.libgdx.Behavior;

import com.wub.game.libgdx.GameComponent;

/**
 * Created by R on 8/11/2014.
 */
public class Transform extends GameComponent {
    private float x, y;
    private float originX, originY;
    private float scaleX, scaleY;
    private float width, height;
    private float rotation;

    public Transform() {
        this(0, 0);
    }

    public Transform(float x, float y) {
        this(x, y, 0, 0, 1, 1, 0);
    }

    public Transform(float x, float y, float originX, float originY, float scaleX, float scaleY, float rotation) {
        super();
        this.x = x;
        this.y = y;
        this.originX = originX;
        this.originY = originY;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.width = 0;
        this.height = 0;
        this.rotation = rotation;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getRotation() {
        return rotation;
    }

    /***
     * Set the rotation in degrees.
     * @param rotation angle in degrees.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setOrigin(float x, float y) {
        setOriginX(x);
        setOriginY(y);
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getWidth() {
        return this.width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setSize(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    public void rotateBy(float angle) {
        setRotation(getRotation() + angle);
    }

    @Override
    public void update(float deltaTime) {

    }
}
