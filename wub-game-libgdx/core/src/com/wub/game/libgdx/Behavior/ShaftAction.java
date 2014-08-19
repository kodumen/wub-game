package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.GameObject;

/**
 * Created by R on 8/12/2014.
 */
public class ShaftAction extends GameComponent {
    public static final int STOP = 0, CLKWISE = -1, CCLKWISE = 1;
    private float speed;
    private float maxSpeed;
    private int direction;
    private boolean touched;
    private int initDirection;
    private Array<Collider> itemColliders;
    private Array<ItemType> itemTypes;
    private Collider collider;

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
        collider = collider == null ? (Collider) gameObject.getComponent("Collider") : collider;

        if (Gdx.input.isTouched() && !touched) {
            touched = true;
            // Get the itemColliders that collided with the shaft.
            ItemType itemType = getCollidedItemType(itemColliders);
            if (itemType != null) {
                if (itemType.getStatus() != ItemType.IDLE) {
                    itemType.fadeOut();
                    switch (itemType.getType()) {
                        case ItemType.BOMB:
                        /*
                        TODO reduce speed
                        TODO subtract from time
                        TODO shake cam (optional)
                         */
                            break;
                        case ItemType.BONUS:
                        /*
                        TODO increase speed
                        TODO add to time
                         */
                            increaseSpeed(1.25f);
                            break;
                        case ItemType.SCORE1:
                        /*
                        TODO increase speed
                        TODO score +1
                         */
                            increaseSpeed(1.25f);
                            break;
                        case ItemType.SCORE2:
                        /*
                        TODO increase speed
                        TODO score +2
                         */
                            increaseSpeed(1.25f);
                            break;
                        default:
                            break;
                    }
                }
            }
            direction = direction == 0f ? initDirection : -direction;
        } else if (!Gdx.input.isTouched() && touched) touched = false;
        gameObject.transform.rotateBy(direction * speed * deltaTime);
    }

    private ItemType getCollidedItemType(Array<Collider> itemColliders) {
        for (int i = 0; i < itemColliders.size; i++) {
            if (collider.collidesWith(itemColliders.get(i)))
                return itemTypes.get(i);
        }
        return null;
    }

    /**
     * Set items to check collision with. This would be the items Array.
     *
     * @param items
     */
    public void setItems(Array<GameObject> items) {
        for (int i = 0; i < items.size; i++) {
            itemColliders.add((Collider) items.get(i).getComponent("Collider"));
            itemTypes.add((ItemType) items.get(i).getComponent("ItemType"));
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

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    private void increaseSpeed(float speed) {
        this.speed = this.speed < this.maxSpeed ? this.speed * speed : maxSpeed;
    }

    public void setInitDirection(int initDirection) {
        this.initDirection = initDirection;
    }
}
