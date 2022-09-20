package com.example.opengl3renderer.ui.object2d.card;

import android.content.Context;

import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Texture;
import com.example.opengl3renderer.ui.object2d.Material2D;

public class CardMaterial extends Material2D {
    boolean textured;
    Vec4 color;
    Texture texture;
    float roundness;

    public CardMaterial(Context context){
        shader = new CardShader(context);
        textured = false;
        color = new Vec4(1.0f);
        texture = null;
        roundness = 0.0f;
    }
    public CardMaterial(Context context, Vec4 color){
        shader = new CardShader(context);
        textured = false;
        this.color = color;
        texture = null;
        roundness = 0.0f;
    }
    public CardMaterial(Context context, Texture texture){
        shader = new CardShader(context);
        textured = true;
        color = null;
        this.texture = texture;
        roundness = 0.0f;
    }
    public CardMaterial(Context context, Vec4 color, float roundness){
        shader = new CardShader(context);
        textured = false;
        this.color = color;
        texture = null;
        this.roundness = roundness;
    }
}
