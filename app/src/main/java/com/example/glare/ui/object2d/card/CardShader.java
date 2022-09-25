package com.example.glare.ui.object2d.card;

import android.content.Context;

import com.example.glare.math.Vec2;
import com.example.glare.math.Vec4;
import com.example.glare.renderer.Uniform;
import com.example.glare.ui.object2d.Object2DShader;

public class CardShader extends Object2DShader{
    Uniform textured;
    Uniform color;
    Uniform texture;
    Uniform roundedCorners;

    public CardShader(Context context){
        super(context, "shaders/ui/card.vs", "shaders/ui/card.fs");
        textured = new Uniform("uTextured", shaderProgramId);
        color = new Uniform("uColor", shaderProgramId);
        texture = new Uniform("uTexture", shaderProgramId);
        roundedCorners = new Uniform("uRoundedCorners", shaderProgramId);
    }

    public void setColor(Vec4 color){
        this.color.setObject(color);
        this.color.sendToShader();
    }

    public void setRoundCorners(Vec2[] roundCorners){
        this.roundedCorners.setObject(roundCorners);
        this.roundedCorners.sendToShader();
    }
}
