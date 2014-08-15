package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.math.MathUtils;
import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.WubGame;

/**
 * Created by R on 8/12/2014.
 */
public class ItemType extends GameComponent {
    public static final int NONE = 0, SCORE1 = 1, SCORE2 = 2, BONUS = 3, BOMB = 4;
    public static final int ACTIVE = 0, INACTIVE = 2, IDLE = 3;
    private int status;
    private int type;
    private float timer;
    private float fadeDuration;
    private boolean startFadeIn, startFadeOut;

    public ItemType() {
        super();
        type = NONE;
        timer = 0f;
        status = this.IDLE;
        fadeDuration = 0f;
        startFadeIn = startFadeOut = false;
    }

    @Override
    public void update(float deltaTime) {
        timer += startFadeIn || startFadeOut ? deltaTime : 0f;

        if(startFadeIn) gameObject.render.setOpacity(timer / fadeDuration);
        else if(startFadeOut) gameObject.render.setOpacity(1f - (timer / fadeDuration));

        if(timer >= fadeDuration) {
            timer = 0f;
            if(startFadeOut) {
                setType(NONE);
                setTextureFromType();
                status = IDLE;
            }
            startFadeOut = startFadeIn = false;
        }

    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }

    /**
     * Sets the duration of fade action.
     * @param fadeDuration
     */
    public void setFadeDuration(float fadeDuration) {
        this.fadeDuration = fadeDuration;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     * Picks a random type from SCORE1 to BOMB (NONE not included) and assigns the appropriate texture.
     */
    public void randomizeType() {
        setType((int)(MathUtils.random() * (BOMB - SCORE1 + 1)) + SCORE1);
        setTextureFromType();
    }

    /**
     * Set the the texture of the item based on its type.
     * Call this every time you call setType().
     * Textures are dependent on PIE_X textures set in WubGame class.
     */
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
        status = getType() == this.NONE ? this.IDLE : this.ACTIVE;
    }

    /**
     * Start fading in of an item.
     * Use it to generate a random item and fade it in to the scene.
     * Fade in duration is set using setFadeDuration().
     */
    public void fadeIn() {
        startFadeIn = true;
        startFadeOut = false;
        timer = 0f;
        status = this.ACTIVE;       // Setting this to active enables the item to be hit even if it's opacity is not yet full.
        gameObject.render.setOpacity(0f);   // Hide the texture first.
        randomizeType();
    }

    /**
     * Start fading out an item.
     * Use it to set item status to inactive, preventing it from getting hit.
     * Fades out the item from the scene.
     * Fade out duration is set using setFadeDuration();
     */
    public void fadeOut() {
        startFadeOut = true;
        startFadeIn = false;
        timer = 0f;
        status = this.INACTIVE;
        gameObject.render.setOpacity(1f);
    }
}
