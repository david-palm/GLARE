package com.example.glare.math;

public class Vec3 {
    public float x,y,z;
    public Vec3(){
        x = y = z = 0.0f;
    }
    public Vec3(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3(Vec2 v, float z){
        x = v.x;
        y = v.y;
        this.z = z;
    }

    public Vec3(float value){
        x = value;
        y = value;
        z = value;
    }
    public Vec3(float[] values){
        this.x = values[0];
        this.y = values[1];
        this.z = values[2];
    }

    public Vec2 getVec2(){
        return new Vec2(x,y);
    }

    public Vec3 add(Vec3 v){
        x += v.x;
        y += v.y;
        z += v.z;
        return this;
    }

    public static Vec3 add(Vec3 v1, Vec3 v2){
        Vec3 result = new Vec3();

        result.x = v1.x + v2.x;
        result.y = v1.y + v2.y;
        result.z = v1.z + v2.z;

        return result;
    }

    public Vec3 add(float scalar){
        x += scalar;
        y += scalar;
        z += scalar;
        return this;
    }

    public static Vec3 add(Vec3 v, float scalar){
        Vec3 result = new Vec3();

        result.x = v.x + scalar;
        result.y = v.y + scalar;
        result.z = v.z + scalar;

        return result;
    }

    public Vec3 subtract(Vec3 v){
        x -= v.x;
        y -= v.y;
        z -= v.z;
        return this;
    }

    public static Vec3 subtract(Vec3 v1, Vec3 v2){
        Vec3 result = new Vec3();

        result.x = v1.x - v2.x;
        result.y = v1.y - v2.y;
        result.z = v1.z - v2.z;

        return result;
    }

    public Vec3 subtract(float scalar){
        x -= scalar;
        y -= scalar;
        z -= scalar;
        return this;
    }

    public static Vec3 subtract(Vec3 v, float scalar){
        Vec3 result = new Vec3();

        result.x = v.x - scalar;
        result.y = v.y - scalar;
        result.z = v.z - scalar;

        return result;
    }

    public Vec3 negate(){
        x = -x;
        y = -y;
        z = -z;
        return this;
    }

    public static Vec3 negate(Vec3 v){
        Vec3 result = new Vec3();

        result.x = -v.x;
        result.y = -v.y;
        result.z = -v.z;

        return result;
    }

    public float length(){
        return (float) Math.sqrt(x*x + y*y + z*z);
    }

    public static float length(Vec3 v){
        return (float) Math.sqrt(v.x*v.x + v.y*v.y + v.z*v.z);

    }

    public Vec3 normalize(){
        float length = length();
        x = x/length;
        y = y/length;
        z = z/length;

        return this;
    }

    public static Vec3 normalize(Vec3 v){
        Vec3 result = new Vec3();

        float length = v.length();
        result.x = v.x/length;
        result.y = v.y/length;
        result.z = v.z/length;

        return result;
    }

    public Vec3 multiply(float scalar){
        x *= scalar;
        y *= scalar;
        z *= scalar;

        return this;
    }

    public static Vec3 multiply(Vec3 v, float scalar){
        Vec3 result = new Vec3();

        result.x = v.x * scalar;
        result.y = v.y * scalar;
        result.z = v.z * scalar;

        return result;
    }

    public Vec3 multiply(Vec3 v){
        x *= v.x;
        y *= v.y;
        z *= v.z;
        return this;
    }

    public static Vec3 multiply(Vec3 v1, Vec3 v2){
        Vec3 result = new Vec3();

        result.x = v1.x * v2.x;
        result.y = v1.y * v2.y;
        result.z = v1.z * v2.z;

        return result;
    }

    public float dot(Vec3 v){
        return (x * v.x + y * v.y + z * v.z);
    }

    public static float dot(Vec3 v1, Vec3 v2){
        return (v1.x * v2.x + v1.y * v2.y + v1.z * v2.z);
    }

    public Vec3 cross(Vec3 v){
        x = y * v.z - z * v.y;
        y = z * v.x - x * v.z;
        z = x * v.y - y * v.x;

        return this;
    }

    public static Vec3 cross(Vec3 v1, Vec3 v2){
        Vec3 result = new Vec3();

        result.x = v1.y * v2.z - v1.z * v2.y;
        result.y = v1.z * v2.x - v1.x * v2.z;
        result.z = v1.x * v2.y - v1.y * v2.x;

        return result;
    }

}
