package com.example.glare.ui.object2d.arrow;

import android.content.Context;

import com.example.glare.math.Mat4;
import com.example.glare.math.Vec2;
import com.example.glare.ui.object2d.BasicMaterial2D;
import com.example.glare.ui.object2d.Object2D;
import com.example.glare.ui.object2d.Rectangle;

public class ArrowHead extends Object2D {
    BasicMaterial2D material;
    float angle;
    float length;
    float thickness;
    Mat4[] models;

    public ArrowHead(Context context){
        this(context, (float) Math.PI/6, 0.1f, 0.02f, new Vec2(), 0.0f);
    }

    public ArrowHead(Context context, float angle, float length, float thickness, Vec2 position, float rotation){
        super(new Rectangle(), new BasicMaterial2D(context));
        this.material = new BasicMaterial2D(context);
        this.position = position;
        this.angle = angle;
        this.length = length;
        this.thickness = thickness;
        models = new Mat4[2];
        models[0] = new Mat4(new Vec2(), new Vec2(length, thickness), angle);
        models[1] = new Mat4(new Vec2(), new Vec2(length, thickness), -angle);
    }



    @Override
    public boolean isInside(float x, float y) {
        return false;
    }
}
