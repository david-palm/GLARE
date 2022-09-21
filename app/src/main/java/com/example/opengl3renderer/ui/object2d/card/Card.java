package com.example.opengl3renderer.ui.object2d.card;

import android.content.Context;
import android.util.Log;

import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.ui.object2d.Object2D;
import com.example.opengl3renderer.ui.object2d.Rectangle;

public class Card extends Object2D {
    CardMaterial material;

    public Card (Context context) {
        super(new Rectangle(), new CardMaterial(context));
        material = new CardMaterial(context);
    }
    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation) {
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
    }

    public Card(CardMaterial material) {
        super(new Rectangle(), material);
        this.material = material;
    }

    @Override
    public boolean isInside(float x, float y) {
        if(x >= (position.x - scale.x) && y >= (position.y - scale.y)) {
            if(x <= (position.x + scale.x) && y <= (position.y + scale.y)) {
                return true;
            }
        }
        return false;
    }

    public void onRender(){
        // Binding shader and make OpenGL calls
        material.getShader().bind();
        // Sending matrices to shader
        material.getShader().setModel(model);
        material.getShader().setColor(material.getColor());
        mesh.onRender();
    }
}
