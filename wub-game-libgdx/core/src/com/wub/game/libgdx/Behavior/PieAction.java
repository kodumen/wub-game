package com.wub.game.libgdx.Behavior;

import com.wub.game.libgdx.GameComponent;
import com.wub.game.libgdx.GameObject;

/**
 * Created by R on 8/12/2014.
 */
public class PieAction extends GameComponent {
    private GameObject pieSlice;

    public void create() {
        for(int i = 0; i < 16; i++) {
            GameObject copy = new GameObject(pieSlice.getName() + i);
            copy.render.setTexture(pieSlice.render.getTexture());
            copy.transform.setX(pieSlice.transform.getX());
            copy.transform.setY(pieSlice.transform.getY());
            copy.transform.setOriginX(pieSlice.transform.getOriginX());
            copy.transform.setOriginY(pieSlice.transform.getOriginY());
            copy.transform.setScaleX(pieSlice.transform.getScaleX());
            copy.transform.setScaleY(pieSlice.transform.getScaleY());
            copy.transform.setRotation((i * 360f / 16f) + pieSlice.transform.getRotation());

            if(pieSlice.hasComponent("Collider")) {
                Collider sliceCollider = (Collider)pieSlice.getComponent("Collider");
                Collider copyCollider;
                copy.addComponent(new Collider());
                copyCollider = (Collider)copy.getComponent("Collider");
                copyCollider.setPolygon(sliceCollider.getPolygon().getVertices());
                copyCollider.update(0);
            }

            gameObject.add(copy);
        }
    }

    @Override
    public void update(float deltaTime) {

    }

    public void setPieSlice(GameObject pieSlice) {
        this.pieSlice = pieSlice;
    }
}
