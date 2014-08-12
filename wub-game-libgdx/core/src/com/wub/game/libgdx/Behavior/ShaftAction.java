package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.Gdx;
import com.wub.game.libgdx.GameComponent;

/**
 * Created by R on 8/12/2014.
 */
public class ShaftAction extends GameComponent {
    private float speed;
    private int direction;
    private boolean touched;

    private int initDirection;

    public static final int STOP = 0, CLKWISE = -1, CCLKWISE = 1;

    public ShaftAction() {
        super();
        speed = 0f;
        direction = STOP;
        touched = false;
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

    @Override
    public void update(float deltaTime) {
        if(Gdx.input.isTouched() && !touched) {
            touched = true;
            direction = direction == 0f ? initDirection : -direction;
        }
        else if(!Gdx.input.isTouched() && touched) touched = false;
        gameObject.transform.rotateBy(direction * speed * deltaTime);
    }
}
