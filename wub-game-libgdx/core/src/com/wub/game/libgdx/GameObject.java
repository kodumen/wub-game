package com.wub.game.libgdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Iterator;

/**
 * Created by R on 8/10/2014.
 */
public class GameObject {
    private String name;
    public float x, y;
    public float originX, originY;
    public float rotation;
    public Texture texture;
    private OrderedMap<String, GameBehavior> components;
    private GameObject parent;
    private Array<GameObject> children;
    private Iterator<GameBehavior> compIterator;

    public GameObject(String name) {
        this.name = name;
        x = y = 0;
        originX = originY = 0;
        rotation = 0;
        texture = null;
        components = new OrderedMap<String, GameBehavior>(5);
        children = null;
        compIterator = null;
        parent = null;
    }

    public GameObject() { this(""); }

    public void addComponent(GameBehavior component) {
        components.put(component.getClass().getName(), component);
    }

    public String getName() { return name; }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setOrigin(float x, float y) {
        this.originX = x;
        this.originY = y;
    }

    public boolean hasTexture() { return texture != null; }

    public float getWidth() { return texture.getWidth(); }
    public float getHeight() { return texture.getHeight(); }

    public boolean hasComponent(String key) { return components.containsKey(key); }

    public GameBehavior getComponent(String key) {
        return components.get(key);
    }

    public void update(float deltaTime) {
        if(components.size > 0) {
            compIterator = components.values();
            GameBehavior comp;
            while(compIterator.hasNext()) {
                comp = compIterator.next();
                comp.update(deltaTime);
            }
        }

        if(children != null) for (int i = 0; i < children.size; i++)
            children.get(i).update(deltaTime);
    }

    public void add(GameObject child, int zIndex) {
        if(children == null) children = new Array<GameObject>();

        if(zIndex < children.size - 1 && zIndex >= 0) children.insert(zIndex, child);
        else children.add(child);
    }

    public void add(GameObject child) {
        add(child, -1);
    }

    public Array<GameObject> getChildren() { return children; }

    public void remove(GameObject child) {
        // Remove children of child first
        Array<GameObject> root = child.getChildren();
        if (root != null) {
            for(int i = root.size - 1; i >= 0; i--) {
                child.remove(root.get(i));
            }
        }

        // Remove child
        children.removeValue(child, true);
    }

    public GameObject getChild(int index) { return children.get(index); }

    public boolean hasChildren() { return children != null; }

    public void setParent(GameObject gameObject) {
        GameObject oldParent = this.parent;
        this.parent = gameObject;
        gameObject.add(this);
        oldParent.remove(this);
    }

    public GameObject getParent() { return this.parent; }
}
