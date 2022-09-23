package com.example.opengl3renderer.inertia;

import android.util.Log;

import com.example.opengl3renderer.math.Vec2;

public class Cosine extends Function{
    float amplitude;
    long length;
    Vec2 offset;

    public Cosine(){
        this(1.0f, 1, new Vec2());
    }

    public Cosine(long length){
        this(1.0f, length, new Vec2());
    }

    public Cosine(float amplitude, long length, Vec2 offset){
        this.amplitude = amplitude;
        this.length = length;
        this.offset = offset;
    }
    @Override
    public float getValue(long time) {
        return (float) (amplitude * Math.cos((time * Math.PI / length) + offset.x * Math.PI) + offset.y);
    }
}
