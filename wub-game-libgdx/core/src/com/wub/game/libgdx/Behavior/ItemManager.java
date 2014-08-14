package com.wub.game.libgdx.Behavior;

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

    public void create() {
        // Add disc items
        Collider itemCollider = (Collider) item.getComponent("Collider");
        ItemType itemType = (ItemType)item.getComponent("ItemType");
        for(int i = 0; i < itemCount; i++) {
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
            copyItem.setCoolDownTime(itemType.getCoolDownTime());
            copyItem.setType(itemType.getType());
            copyItem.setTextureFromType();

            gameObject.add(copy);
        }

        // Set starting items
        for(int i = 0; i < startItemCount; i++) {
            int itemNum;
            ItemType itemType1;
            do {
                itemNum = (int)(Math.random() * (itemCount - 1)) + 1;
                itemType1 = (ItemType)gameObject.getChild(itemNum).getComponent("ItemType");
            } while(itemType1.getType() != ItemType.NONE);
            itemType1.randomizeType();
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    public void setItem(GameObject item) {
        this.item = item;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getStartItemCount() {
        return startItemCount;
    }

    public void setStartItemCount(int startItemCount) {
        this.startItemCount = startItemCount;
    }
}
