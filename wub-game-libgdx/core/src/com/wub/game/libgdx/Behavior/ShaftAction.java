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
    private Array<Collider> itemColliders;
    private Array<ItemType> itemTypes;
    private Collider collider;

    public static final int STOP = 0, CLKWISE = -1, CCLKWISE = 1;

    public ShaftAction() {
        super();
        speed = 0f;
        direction = STOP;
        touched = false;
        collider = null;
        itemColliders = new Array<Collider>();
        itemTypes = new Array<ItemType>();
    }

    @Override
    public void update(float deltaTime) {
        // Get collider component on first run.
        collider = collider == null ? (Collider)gameObject.getComponent("Collider") : collider;

        if(Gdx.input.isTouched() && !touched) {
            touched = true;
            // Get the itemColliders that collided with the shaft.
            direction = direction == 0f ? initDirection : -direction;

        }
        else if(!Gdx.input.isTouched() && touched) touched = false;
        gameObject.transform.rotateBy(direction * speed * deltaTime);
    }

    private GameObject getCollidedItem(Array<GameObject> ) {
        for(int i = 0; i < itemColliders.size; i++) {

        }
    }

    /**
     * Set items to check collision with. This would be the items Array.
     * @param items
     */
    public void setItems(Array<GameObject> items) {
        for(int i = 0; i < items.size; i++) {
            itemColliders.add((Collider)items.get(i).getComponent("Collider"));
            itemTypes.add((ItemType)items.get(i).getComponent("ItemTypes"));
        }
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
