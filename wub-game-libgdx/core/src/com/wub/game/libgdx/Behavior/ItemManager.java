package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.GameObject;

/**
 * Created by R on 8/12/2014.
 */
public class ItemManager extends GameComponent {
    private GameObject item;
    private int itemCount;
    private int startItemCount;     // Number of items to be generated first
    private int maxItemCount;       // Maximum number of items present at the same time.
    private float coolDownTime;     // Time between attempts of item generation.
    private float timer;
    private Array<ItemType> childItemTypes;

    /**
     * Initialize the ItemManager and generate starting items.
     * Number of starting items are based on setStartItemCount.
     * Number of items set by setItemCount().
     * Item to clone is set by setItems().
     */
    public void create() {
        // Clone item
        Collider itemCollider = (Collider) item.getComponent("Collider");
        ItemType itemType = (ItemType)item.getComponent("ItemType");
        for(int i = 0; i < itemCount; i++) {
            // Copy original item's properties to a new instance of a GameObject.
            GameObject copy = new GameObject(item.getName() + i);
            copy.transform.setWidth(item.transform.getWidth());
            copy.transform.setHeight(item.transform.getHeight());
            copy.transform.setX(item.transform.getX());
            copy.transform.setY(item.transform.getY());
            copy.transform.setOriginX(item.transform.getOriginX());
            copy.transform.setOriginY(item.transform.getOriginY());
            copy.transform.setScaleX(item.transform.getScaleX());
            copy.transform.setScaleY(item.transform.getScaleY());
            copy.transform.setRotation((i * 360f / itemCount) + item.transform.getRotation());
            if(item.render.hasTexture()) copy.render.setTexture(item.render.getTexture());

            Collider copyCollider;
            copy.addComponent(new Collider());
            copyCollider = (Collider)copy.getComponent("Collider");
            copyCollider.setPolygon(itemCollider.getPolygon().getVertices());
            copyCollider.update(0);

            ItemType copyItem;
            copy.addComponent(new ItemType());
            copyItem = (ItemType)copy.getComponent("ItemType");
            copyItem.setType(itemType.getType());
            copyItem.setTextureFromType();
            copyItem.setFadeDuration(itemType.getFadeDuration());

            // Add as child of gameObject
            gameObject.add(copy);
        }

        // Generate starting items.
        for(int i = 0; i < startItemCount; i++) {
            int itemNum;
            ItemType itemType1;
            do {
                // Pick a random child from this GameObject.
                itemNum = (int)(MathUtils.random(gameObject.getChildren().size - 1));
                itemType1 = (ItemType)gameObject.getChild(itemNum).getComponent("ItemType");
            } while(itemType1.getType() != ItemType.NONE); // We can't set the type of a child if it already has one so we pick again.
            itemType1.randomizeType();
        }

        childItemTypes = getChildItemTypes();
    }

    @Override
    public void update(float deltaTime) {
        timer += deltaTime;
        if(timer >= coolDownTime) {
            // Count number of ACTIVE items.
            // If it's less than maxItemCount we get items that are IDLE.
            if(countStatus(ItemType.ACTIVE) < maxItemCount) {
                timer = 0;
                // Get IDLE items
                Array<ItemType> idleChildren = new Array<ItemType>();
                for(int i = 0; i < childItemTypes.size; i++)
                    if(childItemTypes.get(i).getStatus() == ItemType.IDLE)
                        idleChildren.add(childItemTypes.get(i));

                // Pick a random type for a random item.
                ItemType randIdle = null;
                do {
                    randIdle = childItemTypes.get(MathUtils.random(childItemTypes.size - 1));
                } while(randIdle.getType() != ItemType.NONE);
                randIdle.fadeIn();
            }
        }
    }

    /**
     * Set the maximum amount of items to be present at the same time.
     * @param maxItemCount
     */
    public void setMaxItemCount(int maxItemCount) {
        this.maxItemCount = maxItemCount;
    }

    public void setCoolDownTime(float coolDownTime) {
        this.coolDownTime = coolDownTime;
    }

    /**
     * Set the item that we clone from.
     * @param item must have Collider and ItemType components.
     */
    public void setItem(GameObject item) {
        this.item = item;
    }

    /**
     * Set the number of items the ItemManager has.
     * @param itemCount
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getStartItemCount() {
        return startItemCount;
    }

    /**
     * Set the number of items to generate at the start of the game.
     * @param startItemCount
     */
    public void setStartItemCount(int startItemCount) {
        this.startItemCount = startItemCount;
    }

    /**
     * Returns an array of the ItemType components of this gameObject's children.
     * @return ItemType array
     */
    private Array<ItemType> getChildItemTypes() {
        Array<ItemType> r = new Array<ItemType>();
        for(int i = 0; i < gameObject.getChildren().size; i++)
            r.add((ItemType)gameObject.getChild(i).getComponent("ItemType"));
        return r;
    }

    /**
     * Returns the number of children whose status is the same as the one specified.
     * @param status status of the child.
     * @return number of children with the same status.
     */
    private int countStatus(int status) {
        int r = 0;
        for(int i = 0; i < childItemTypes.size; i++)
            if(childItemTypes.get(i).getStatus() == status) r++;
        return r;
    }
}
