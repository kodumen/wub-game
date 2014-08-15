package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.wub.game.libgdx.GameComponent;

/**
 * Created by R on 8/11/2014.
 */
public class Render extends GameComponent {
    private Texture texture;
    private Color color;

    public Render() {
        super();
        texture = null;
        color = new Color(1, 1, 1, 1);
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

    public void removeTexture() {
        this.texture = null;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() { return color; }

    /**
     * Set this render's alpha.
     * @param a alpha value between 0 and 1.
     */
    public void setOpacity(float a) {
        this.color.a = a;
    }

    @Override
    public void update(float deltaTime) {

    }
}
