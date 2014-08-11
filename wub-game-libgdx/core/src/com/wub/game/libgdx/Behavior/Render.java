package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.graphics.Texture;
import com.wub.game.libgdx.GameComponent;

/**
 * Created by R on 8/11/2014.
 */
public class Render extends GameComponent {
    private Texture texture;

    public Render() {
        super();
        texture = null;
    }

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

    @Override
    public void update(float deltaTime) {

    }
}
