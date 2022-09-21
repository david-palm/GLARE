package com.example.opengl3renderer.ui.object2d.card;

import android.content.Context;
import android.util.Log;

import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.ui.object2d.Object2D;
import com.example.opengl3renderer.ui.object2d.Rectangle;

public class Card extends Object2D {
    CardMaterial material;
    Vec2[] roundCorners;

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, Vec2[] roundCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundCorners = roundCorners;
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, Vec2 roundCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundCorners = new Vec2[4];
        for(int i = 0; i < 4; i++){
            this.roundCorners[i] = roundCorners;
        }
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, float roundCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundCorners = new Vec2[4];
        for(int i = 0; i < 4; i++){
            this.roundCorners[i] = new Vec2(roundCorners, roundCorners);
        }
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundCorners = new Vec2[4];
    }


    public Card (Context context){
        super(new Rectangle(), new CardMaterial(context));
        material = new CardMaterial(context);
        roundCorners = new Vec2[4];
    }

    public Card(CardMaterial material){
        super(new Rectangle(), material);
        this.material = material;
        roundCorners = new Vec2[4];
    }

    @Override
    public boolean isInside(float x, float y){
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
        material.getShader().setRoundCorners(roundCorners);
        mesh.onRender();
    }

    public Vec2[] getRoundCorners(){
        return roundCorners;
    }

    public void setRoundCorners(Vec2[] roundCorners){
        if(roundCorners.length <= 4){
            for(int i = 0; i < roundCorners.length; i++){
                this.roundCorners[i] = roundCorners[i];
            }
        }
        if(roundCorners.length > 4){
            Log.e("CardMaterial", "A maximum of four corners is allowed");
            this.roundCorners = new Vec2[4];
        }
    }
}
