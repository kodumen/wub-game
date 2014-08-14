package com.wub.game.libgdx;

/**
 * Created by R on 8/10/2014.
 */
public abstract class GameComponent {
    protected GameObject gameObject = null;

    public GameComponent() {
        gameObject = null;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public abstract void update(float deltaTime);

    public abstract void create();
}
