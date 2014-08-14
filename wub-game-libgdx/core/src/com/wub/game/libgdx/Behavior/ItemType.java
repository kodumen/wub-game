package com.wub.game.libgdx.Behavior;

import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.WubGame;

/**
 * Created by R on 8/12/2014.
 */
public class ItemType extends GameComponent {
    public static final int NONE = 0, SCORE1 = 1, SCORE2 = 2, BONUS = 3, BOMB = 4;
    private int type;
    private float timer;
    private float coolDownTime;
    private boolean start;

    public ItemType() {
        super();
        type = NONE;
        timer = 0;
        coolDownTime = 0;
        start = false;
    }

    @Override
    public void update(float deltaTime) {
        timer += start ? deltaTime : 0;
        if(timer >= coolDownTime) {
            timer = 0;
            randomizeType();
            stopTimer();
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getCoolDownTime() {
        return coolDownTime;
    }

    public void setCoolDownTime(float coolDownTime) {
        this.coolDownTime = coolDownTime;
    }

    public void randomizeType() {
        setType((int)(Math.random() * (BOMB - SCORE1 + 1)) + SCORE1);
        setTextureFromType();
    }

    public void setTextureFromType() {
        switch(getType()) {
            case NONE:
                gameObject.render.removeTexture();
                break;
            case SCORE1:
                gameObject.render.setTexture(WubGame.PIE_0);
                break;
            case SCORE2:
                gameObject.render.setTexture(WubGame.PIE_1);
                break;
            case BONUS:
                gameObject.render.setTexture(WubGame.PIE_2);
                break;
            case BOMB:
                gameObject.render.setTexture(WubGame.PIE_3);
                break;
            default:
                break;
        }
    }

    public boolean isStartTimer() {
        return start;
    }

    public void startTimer() {
        this.start = true;
        timer = 0;
    }

    public void stopTimer() {
        this.start = false;
    }
}
