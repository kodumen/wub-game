package com.wub.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by R on 8/5/2014.
 */
public class GameActor extends Actor {
    private Texture texture;

    public void setTexture(Texture texture) { this.texture = texture; }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY());
    }
}
