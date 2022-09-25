package com.example.glare.math;

import android.graphics.Color;

import java.nio.FloatBuffer;

public class Vec4 {
    public float x,y,z,w;

    public Vec4(){
        x = y = z = w = 0.0f;
    }

    public Vec4(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4(Vec3 v, float w){
        x = v.x;
        y = v.y;
        z = v.z;
        this.w = w;
    }

    public Vec4(Vec2 v, float z, float w){
        x = v.x;
        y = v.y;
        this.z = z;
        this.w = w;
    }

    public Vec4(float value){
        x = value;
        y = value;
        z = value;
        w = value;
    }
    public Vec4(float[] values){
        x = values[0];
        y = values[1];
        z = values[2];
        w = values[3];
    }

    public Vec4 (Color color){
        new Vec4(color.red(), color.green(), color.red(), color.alpha());
    }

    public Vec3 getVec3(){
        return new Vec3(x,y,z);
    }

    public Vec2 getVec2(){
        return new Vec2(x,y);
    }

    public Vec4 subtract(Vec4 v){
        x -= v.x;
        y -= v.y;
        z -= v.z;
        w -= v.w;
        return this;
    }

    public Vec4 subtract(float scalar){
        x -= scalar;
        y -= scalar;
        z -= scalar;
        w -= scalar;
        return this;
    }


    public Vec4 add(Vec4 v){
        x += v.x;
        y += v.y;
        z += v.z;
        w += v.w;
        return this;
    }

    public static Vec4 add(Vec4 v1, Vec4 v2){
        Vec4 result = new Vec4();

        result.x = v1.x + v2.x;
        result.y = v1.y + v2.y;
        result.z = v1.z + v2.z;
        result.w = v1.w + v2.w;

        return result;
    }

    public Vec4 add(float scalar){
        x += scalar;
        y += scalar;
        z += scalar;
        w += scalar;
        return this;
    }

    public static Vec4 add(Vec4 v, float scalar){
        Vec4 result = new Vec4();

        result.x = v.x + scalar;
        result.y = v.y + scalar;
        result.z = v.z + scalar;
        result.w = v.w + scalar;

        return result;
    }

    public Vec4 negate(){
        x = -x;
        y = -y;
        z = -z;
        w = -w;
        return this;
    }

    public static Vec4 negate(Vec4 v){
        Vec4 result = new Vec4();

        result.x = -v.x;
        result.y = -v.y;
        result.z = -v.z;
        result.w = -v.w;

        return result;
    }

    public float length(){
        return (float) Math.sqrt(x*x + y*y + z*z + w*w);
    }

    public static float length(Vec4 v){
        return (float) Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z + v.w*v.w);
    }

    public Vec4 normalize(){
        float length = length();
        x = x/length;
        y = y/length;
        z = z/length;
        w = w/length;
        return this;
    }

    public Vec4 normalize(Vec4 v){
        Vec4 result = new Vec4();

        float length = length(v);
        result.x = v.x/length;
        result.y = v.y/length;
        result.z = v.z/length;
        result.w = v.w/length;

        return result;
    }

    public Vec4 multiply(float scalar){
        x *= scalar;
        y *= scalar;
        z *= scalar;
        w *= scalar;
        return this;
    }

    public static Vec4 multiply(Vec4 v, float scalar){
        Vec4 result = new Vec4();

        result.x = v.x * scalar;
        result.y = v.y * scalar;
        result.z = v.z * scalar;
        result.w = v.z * scalar;

        return result;
    }

    public float dot(Vec4 v){
        return (x * v.x + y * v.y + z * v.z + w * v.w);
    }

    public static float dot(Vec4 v1, Vec4 v2){
        return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z + v1.w * v2.w);
    }

    public FloatBuffer getValues(){
        FloatBuffer b = FloatBuffer.allocate(4);
        b.put(x);
        b.put(y);
        b.put(z);
        b.put(w);
        b.flip();
        return b;
    }

    public String toString(){
        return (x + " " + y + " " + z + " " + w);
    }






}
