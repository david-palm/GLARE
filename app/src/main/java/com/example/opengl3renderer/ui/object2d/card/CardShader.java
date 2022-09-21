package com.example.opengl3renderer.ui.object2d.card;

import android.content.Context;

import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Shader;
import com.example.opengl3renderer.renderer.Uniform;
import com.example.opengl3renderer.ui.object2d.Object2DShader;

public class CardShader extends Object2DShader {
    Uniform textured;
    Uniform color;
    Uniform texture;
    Uniform roundCorners;

    public CardShader(Context context){
        super(context, "shaders/ui/card.vs", "shaders/ui/card.fs");
        textured = new Uniform("uTextured", shaderProgramId);
        color = new Uniform("uColor", shaderProgramId);
        texture = new Uniform("uTexture", shaderProgramId);
        roundCorners = new Uniform("uRoundCorners", shaderProgramId);
    }

    public void setColor(Vec4 color){
        this.color.setObject(color);
        this.color.sendToShader();
    }

    public void setRoundCorners(Vec2[] roundCorners){
        this.roundCorners.setObject(roundCorners);
        this.roundCorners.sendToShader();
    }
}
