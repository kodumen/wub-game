package com.wub.game.libgdx.Behavior;

import com.badlogic.gdx.math.MathUtils;
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

    /**
     * Initialize the ItemManager and generate starting items.
     * Number of starting items are based on setStartItemCount.
     * Number of items set by setItemCount().
     * Item to clone is set by setItem().
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

            // Add as child of gameObject
            gameObject.add(copy);
        }

        // Generate starting items.
        for(int i = 0; i < startItemCount; i++) {
            int itemNum;
            ItemType itemType1;
            do {
                // Pick a random child from this GameObject.
                itemNum = (int)(MathUtils.random() * itemCount);
                itemType1 = (ItemType)gameObject.getChild(itemNum).getComponent("ItemType");
            } while(itemType1.getType() != ItemType.NONE); // We can't set the type of a child if it already has one so we pick again.
            itemType1.randomizeType();
        }
    }

    @Override
    public void update(float deltaTime) {

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
}
