package com.wub.game.libgdx.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.wub.game.libgdx.GameActor;

/**
 * Created by R on 8/7/2014.
 */
public class Spin extends Action {
    private GameActor actor = null;

    public static final int CLOCKWISE = -1;
    public static final int COUNTERCLOCKWISE = 1;
    public static final int STOP = 0;

    private float speed = 0f;
    private float initSpeed = 0f;
    private int direction = STOP;
    private boolean keyReleased = true;
    private boolean firstRun = true;

    /***
     * Create a new instance of Spin with initial speed and direction of rotation.
     * @param speed initial speed
     * @param direction initial direction of rotation
     */
    public Spin(float speed, int direction) {
        super();
        this.initSpeed = speed;
        this.direction = direction;
    }

    @Override
    public boolean act(float deltaTime) {
        if(actor == null) actor = (GameActor)getActor();

        // DESKTOP/HTML5
//        if(keyReleased && Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            keyReleased = false;
//
//            if(firstRun) {
//                speed = initSpeed;
//                firstRun = false;
//            }
//            else direction *= -1;
//        }
//        else if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)){
//            keyReleased = true;
//        }

        // ANDROID
        if(keyReleased && Gdx.input.isTouched()) {
            keyReleased = false;

            if(firstRun) {
                speed = initSpeed;
                firstRun = false;
            }
            else direction *= -1;
        }
        else if(!Gdx.input.isTouched()){
            keyReleased = true;
        }

        actor.rotateBy(direction * speed * deltaTime);
        actor.updateCollider();

        return false;   // Return false so as not to finish the act on the first run.
    }
}
