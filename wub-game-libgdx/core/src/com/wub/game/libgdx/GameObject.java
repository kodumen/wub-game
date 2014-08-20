package com.wub.game.libgdx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;
import com.wub.game.libgdx.Behavior.Collider;
import com.wub.game.libgdx.Behavior.Render;
import com.wub.game.libgdx.Behavior.Transform;

import java.util.Iterator;

/**
 * Created by R on 8/10/2014.
 */
public class GameObject {
    public Transform transform;
    public Render render;
    private String name;
    private OrderedMap<String, GameComponent> components;
    private GameObject parent;
    private Array<GameObject> children;

    public GameObject(String name) {
        this.name = name;
        components = new OrderedMap<String, GameComponent>(5);
        children = new Array<GameObject>();
        parent = null;

        //Add default behaviors/components
        addComponent(new Transform());
        addComponent(new Render());

        // Set default behaviors
        transform = (Transform) getComponent("Transform");
        render = (Render) getComponent("Render");
    }

    public GameObject() {
        this("");
    }

    public String getName() {
        return name;
    }

    public boolean hasComponent(String key) {
        return components.containsKey(key);
    }

    public void addComponent(GameComponent component) {
        String compName = component.getClass().getSimpleName();
        compName.replace("[", "");
        compName.replace("]", "");
        components.put(compName, component);
        component.setGameObject(this);
    }

    public GameComponent getComponent(String key) {
        return components.get(key);
    }

    public void update(float deltaTime) {
        if (components.size > 0) {
            Iterator<GameComponent> compIterator = components.values();
            GameComponent comp;
            Collider collider = null;
            while (compIterator.hasNext()) {
                comp = compIterator.next();
                if (comp.getClass() == Collider.class) {
                    collider = (Collider) comp;
                    continue;
                }
                comp.update(deltaTime);
            }
            // Update collider last after all other components are updated.
            // This ensures that the collider will always be inline with the object's current transformation.
            if (collider != null) collider.update(deltaTime);
        }

        // Update children
        if (children != null)
            for (int i = 0; i < children.size; i++)
                children.get(i).update(deltaTime);
    }

    public void add(GameObject child, int zIndex) {
        if (zIndex < children.size - 1 && zIndex >= 0)
            children.insert(zIndex, child);
        else
            children.add(child);
    }

    public void add(GameObject child) {
        add(child, -1);
    }

    public Array<GameObject> getChildren() {
        return children;
    }

    public void remove(GameObject child) {
        // Remove children of child first
        Array<GameObject> root = child.getChildren();
        if (root != null) {
            for (int i = root.size - 1; i >= 0; i--) {
                child.remove(root.get(i));
            }
        }
        // Remove child
        children.removeValue(child, true);
    }

    public GameObject getChild(int index) {
        return children.get(index);
    }

    public boolean hasChildren() {
        return children.size > 0;
    }

    public GameObject getParent() {
        return this.parent;
    }

    public void setParent(GameObject gameObject) {
        this.parent = gameObject;
    }
}
