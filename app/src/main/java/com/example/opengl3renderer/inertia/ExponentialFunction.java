package com.example.opengl3renderer.inertia;

import android.util.Log;

import com.example.opengl3renderer.math.Vec2;

public class ExponentialFunction extends Function{
    float exponent;
    float offsetX;
    float offsetY;
    float scale;

    public ExponentialFunction(float exponent){
        this(exponent, 0, 0, 1);
    }

    public ExponentialFunction(float exponent, float duration){
        this(exponent, duration, 0, (float) (1 / Math.pow(duration, exponent)));
    }

    public ExponentialFunction(float exponent, float offsetX, float offsetY, float scale){
        this.exponent = exponent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scale = scale;
    }
    @Override
    public float getValue(long time) {
        return (float) Math.pow((time - offsetX), 2) * scale + offsetY;
    }
}
