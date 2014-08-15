package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.GameObject;

/**
 * Created by R on 8/12/2014.
 */
public class ShaftAction extends GameComponent {
    private float speed;
    private int direction;
    private boolean touched;

    private int initDirection;
    private Array<GameObject> items;

    public static final int STOP = 0, CLKWISE = -1, CCLKWISE = 1;

    public ShaftAction() {
        super();
        speed = 0f;
        direction = STOP;
        touched = false;
        items = null;
    }

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isTouched() && !touched) {
            touched = true;
            direction = direction == 0f ? initDirection : -direction;
        }
        else if(!Gdx.input.isTouched() && touched) touched = false;
        gameObject.transform.rotateBy(direction * speed * deltaTime);
    }

    /**
     * Set items to check collision with. This would be the items Array.
     * @param items
     */
    public void setItems(Array<GameObject> items) {
        this.items = items;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setInitDirection(int initDirection) {
        this.initDirection = initDirection;
    }
}
