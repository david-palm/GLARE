package com.example.opengl3renderer.ui.object2d.card;

import android.content.Context;
import android.util.Log;

import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.ui.object2d.Object2D;
import com.example.opengl3renderer.ui.object2d.Rectangle;

public class Card extends Object2D {
    CardMaterial material;
    Vec2[] roundedCorners;

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, Vec2[] roundedCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundedCorners = new Vec2[4];
        for(int i = 0; i < roundedCorners.length; i++){
            this.roundedCorners[i] = roundedCorners[i];
        }
        for(int i = roundedCorners.length; i < 4; i++){
            this.roundedCorners[i] = new Vec2();
        }
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, Vec2 roundedCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundedCorners = new Vec2[4];
        for(int i = 0; i < 4; i++){
            this.roundedCorners[i] = roundedCorners;
        }
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation, float roundedCorners){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundedCorners = new Vec2[4];
        for(int i = 0; i < 4; i++){
            this.roundedCorners[i] = new Vec2(roundedCorners, roundedCorners);
        }
    }

    public Card(CardMaterial material, Vec2 position, Vec2 scale, float rotation){
        super(new Rectangle(), material, position, scale, rotation);
        this.material = material;
        this.roundedCorners = new Vec2[4];
    }


    public Card (Context context){
        super(new Rectangle(), new CardMaterial(context));
        material = new CardMaterial(context);
        roundedCorners = new Vec2[4];
    }

    public Card(CardMaterial material){
        super(new Rectangle(), material);
        this.material = material;
        roundedCorners = new Vec2[4];
    }

    @Override
    public boolean isInside(float x, float y){
        if(x >= (position.x - scale.x) && x <= (position.x + scale.x)) {
            if(y >= (position.y - scale.y) && y <= (position.y + scale.y)) {
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
        material.getShader().setRoundCorners(roundedCorners);
        mesh.onRender();
    }

    public Vec2[] getRoundedCorners(){
        return roundedCorners;
    }

    public void setRoundedCorners(Vec2[] roundedCorners){
        if(roundedCorners.length <= 4){
            for(int i = 0; i < roundedCorners.length; i++){
                this.roundedCorners[i] = roundedCorners[i];
            }
        }
        if(roundedCorners.length > 4){
            Log.e("CardMaterial", "A maximum of four corners is allowed");
            this.roundedCorners = new Vec2[4];
        }
    }
}
