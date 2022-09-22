package com.example.opengl3renderer.ui.object2d.arrow;

import android.content.Context;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.ui.object2d.BasicMaterial2D;
import com.example.opengl3renderer.ui.object2d.Object2D;
import com.example.opengl3renderer.ui.object2d.Rectangle;

public class ArrowHead extends Object2D {
    BasicMaterial2D material;
    float angle;
    float length;
    float thickness;
    Mat4[] models;

    public ArrowHead(Context context){
        this(context, new Vec2(), (float) Math.PI/6, 0.1f, 0.02f);
    }

    public ArrowHead(Context context, Vec2 position, float angle, float length, float thickness){
        super(new Rectangle(), new BasicMaterial2D(context));
        this.material = new BasicMaterial2D(context);
        this.position = position;
        this.angle = angle;
        this.length = length;
        this.thickness = thickness;
        models = new Mat4[2];
    }

    @Override
    public boolean isInside(float x, float y) {
        return false;
    }
}
