package com.wub.game.libgdx;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.Iterator;

/**
 * Created by R on 8/10/2014.
 */
public class GameObject {
    private String name;
    private Vector2 position;
    private float rotation;
    private OrderedMap<String, GameComponent> components;
    private Array<GameObject> children;
    private Iterator<GameComponent> compIterator;

    public GameObject(String name) {
        this.name = name;
        this.position = new Vector2();
        rotation = 0;
        components = new OrderedMap<String, GameComponent>(5);
        children = null;
        compIterator = null;
    }

    public GameObject() { this(""); }

    public void addComponent(GameComponent component) {
        components.put(component.getClass().getName(), component);
    }

    public String getName() { return name; }

    public boolean hasComponent(String key) { return components.containsKey(key); }

    public GameComponent getComponent(String key) {
        return components.get(key);
    }

    public void update(float deltaTime) {
        if(components.size > 0) {
            compIterator = components.values();
            GameComponent comp;
            while(compIterator.hasNext()) {
                comp = compIterator.next();
                comp.update(deltaTime);
            }
        }

        if(children != null) {
            for(int i = 0; i < children.size; i++)
                children.get(i).update(deltaTime);
        }
    }
}
