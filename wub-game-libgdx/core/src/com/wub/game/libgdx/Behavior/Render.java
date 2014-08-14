package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.graphics.Texture;
import com.wub.game.libgdx.GameComponent;

/**
 * Created by R on 8/11/2014.
 */
public class Render extends GameComponent {
    private Texture texture;

//    public Render() {
//        super();
//
//    }

    public boolean hasTexture() {
        return texture != null;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
        gameObject.transform.setWidth(this.texture.getWidth());
        gameObject.transform.setHeight(this.texture.getHeight());
    }

    public void removeTexture() {
        this.texture = null;
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void create() {
        texture = null;
    }
}
