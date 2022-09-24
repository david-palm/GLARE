package com.example.opengl3renderer.inertia;

import android.util.Log;

import com.example.opengl3renderer.math.Vec2;

public class ExponentialFunction extends Function{
    float amplitude;
    float exponent;
    float offsetX;
    float offsetY;
    float scale;

    public ExponentialFunction(float exponent){
        this(1, exponent, 0, 0, 1);
    }

    public ExponentialFunction(float exponent, float duration){
        this(exponent, 1, duration, 0, (float) (1 / Math.pow(duration, exponent)));
    }

    public ExponentialFunction(float exponent, float amplitude, float offsetX, float offsetY, float scale){
        this.exponent = exponent;
        this.amplitude = amplitude;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.scale = scale;
    }
    @Override
    public float getValue(long time){
        Log.d("Exponential Function", "" + amplitude * (float) Math.pow((time - offsetX), 2) * scale + offsetY);
        return amplitude * (float) Math.pow((time - offsetX), 2) * scale + offsetY;
    }

    public void setAmplitude(float amplitude){
        this.amplitude = amplitude;
    }

    public void setOffsetY(float offsetY){
        this.offsetY = offsetY;
    }
}
