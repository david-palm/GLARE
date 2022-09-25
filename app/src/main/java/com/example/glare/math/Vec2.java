package com.example.glare.math;

public class Vec2 {
    public float x,y;

    public Vec2(){
        x = y = 0.0f;
    }
    public Vec2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vec2(float value){
        x = value;
        y = value;
    }
    public Vec2(float[] values){
        this.x = values[0];
        this.y = values[1];
    }

    public Vec2 add(Vec2 v){
        x += v.x;
        y += v.y;
        return this;
    }

    public static Vec2 add(Vec2 v1, Vec2 v2){
        Vec2 result = new Vec2();

        result.x = v1.x + v2.x;
        result.y = v1.y + v2.y;

        return result;
    }

    public Vec2 add(float scalar){
        x += scalar;
        y += scalar;
        return this;
    }

    public static Vec2 add(Vec2 v, float scalar){
        Vec2 result = new Vec2();

        result.x = v.x + scalar;
        result.y = v.y + scalar;

        return result;
    }

    public Vec2 subtract(Vec3 v){
        x -= v.x;
        y -= v.y;
        return this;
    }

    public static Vec2 subtract(Vec2 v1, Vec2 v2){
        Vec2 result = new Vec2();

        result.x = v1.x - v2.x;
        result.y = v1.y - v2.y;

        return result;
    }

    public Vec2 subtract(float scalar){
        x -= scalar;
        y -= scalar;
        return this;
    }

    public static Vec2 subtract(Vec2 v, float scalar){
        Vec2 result = new Vec2();

        result.x = v.x - scalar;
        result.y = v.y - scalar;

        return result;
    }

    public Vec2 negate(){
        x = -x;
        y = -y;
        return this;
    }

    public static Vec2 negate(Vec2 v){
        Vec2 result = new Vec2();

        result.x = -v.x;
        result.y = -v.y;

        return result;
    }


    public float length(){
        return (float) Math.sqrt(x*x + y*y);
    }

    public static float length(Vec2 v){
        return (float) Math.sqrt(v.x*v.x + v.y*v.y);
    }

    public Vec2 normalize(){
        float length = length();
        x = x/length;
        y = y/length;
        return this;
    }

    public static Vec2 normalize(Vec2 v){
        Vec2 result = new Vec2();

        float length = length(v);
        result.x = v.x/length;
        result.y = v.y/length;

        return result;
    }

    public Vec2 multiply(float scalar){
        x *= scalar;
        y *= scalar;
        return this;
    }

    public static Vec2 multiply(Vec2 v, float scalar){
        Vec2 result = new Vec2();

        result.x = v.x * scalar;
        result.y = v.y * scalar;

        return result;
    }

    public float dot(Vec2 v){
        return (x * v.x + y * v.y);
    }

    public static float dot(Vec2 v1, Vec2 v2){
        return (v1.x * v2.x + v1.y * v2.y);
    }

    public float cross(Vec2 v){
        return (x * v.y + y * v.x);
    }


}
