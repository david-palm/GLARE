package com.example.opengl3renderer.ui.object2d;

import android.content.Context;

import com.example.opengl3renderer.math.Vec4;

public class BasicMaterial2D extends Material2D{
    BasicObject2DShader shader;
    Vec4 color;

    public BasicMaterial2D(Context context) {
        this(context, new Vec4());
    }

    public BasicMaterial2D(Context context, Vec4 color){
        shader = new BasicObject2DShader(context);
        this.color = color;
    }
}
