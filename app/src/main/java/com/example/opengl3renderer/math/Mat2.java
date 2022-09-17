package com.example.opengl3renderer.math;

import java.nio.FloatBuffer;

public class Mat2 {
    public float x1, x2, y1, y2;

    public Mat2(){
        x1 = y2 = 1.0f;
        x2 = y1 = 1.0f;
    }

    public Mat2(float value){
        x1 = value;
        x2 = 0;
        y1 = value;
        y2 = 0;
    }
    public Mat2 (Vec2 v1, Vec2 v2){
        x1 = v1.x;
        y1 = v1.y;
        x2 = v2.x;
        y2 = v2.y;
    }

    public Mat2 (float x1, float x2, float y1, float y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public float getX1(){
        return x1;
    }

    public float getX2(){
        return x2;
    }

    public float getY1(){
        return y1;
    }

    public float getY2(){
        return y2;
    }

    public Vec2 getFirstColumn(){
        return new Vec2(x1,y1);
    }

    public Vec2 getSecondColumn(){
        return new Vec2(x2,y2);
    }

    public Mat2 add(Mat2 mat){
        x1 += mat.x1;
        x2 += mat.x2;
        y1 += mat.y1;
        y2 += mat.y2;
        return this;
    }

    public static Mat2 add(Mat2 mat1, Mat2 mat2){
        Mat2 result = new Mat2();

        result.x1 = mat1.x1 + mat2.x1;
        result.x2 = mat1.x2 + mat2.x2;
        result.y1 = mat1.y1 + mat2.y1;
        result.y2 = mat1.y2 + mat2.y2;

        return result;
    }

    public Mat2 subtract(Mat2 mat){
        x1 -= mat.x1;
        x2 -= mat.x2;
        y1 -= mat.y1;
        y2 -= mat.y2;
        return this;
    }

    public static Mat2 subtract(Mat2 mat1, Mat2 mat2){
        Mat2 result = new Mat2();

        result.x1 = mat1.x1 - mat2.x1;
        result.x2 = mat1.x2 - mat2.x2;
        result.y1 = mat1.y1 - mat2.y1;
        result.y2 = mat1.y2 - mat2.y2;

        return result;
    }


    public Mat2 multiply(float scalar){
        x1 *= scalar;
        x2 *= scalar;
        y1 *= scalar;
        y2 *= scalar;
        return this;
    }

    public static Mat2 multiply(Mat2 mat1, float scalar){
        Mat2 result = new Mat2();

        result.x1 = mat1.x1 * scalar;
        result.x2 = mat1.x2 * scalar;
        result.y1 = mat1.y1 * scalar;
        result.y2 = mat1.y2 * scalar;

        return result;
    }


    public Mat2 multiply(Mat2 mat){
        x1 = x1 * mat.x1 + x2 * mat.y1;
        x2 = x1 * mat.x2 + x2 * mat.y2;
        y1 = y1 * mat.x1 + y2 * mat.y1;
        y2 = y1 * mat.x2 + y2 * mat.y2;
        return this;
    }

    public static Mat2 multiply(Mat2 mat1, Mat2 mat2){
        Mat2 result = new Mat2();

        result.x1 = mat1.x1 * mat2.x1 + mat1.x2 * mat2.y1;
        result.x2 = mat1.x1 * mat2.x2 + mat1.x2 * mat2.y2;
        result.y1 = mat1.y1 * mat2.x1 + mat1.y2 * mat2.y1;
        result.y2 = mat1.y1 * mat2.x2 + mat1.y2 * mat2.y2;

        return result;
    }


    public Vec2 multiply(Vec2 v){
        return new Vec2(x1 * v.x + x2 * v.y, y1 * v.x + y2 * v.y);
    }

    public static Vec2 multiply(Mat2 mat, Vec2 v){
        return new Vec2(mat.x1 * v.x + mat.x2 * v.y, mat.y1 * v.x + mat.y2 * v.y);
    }

    public static Mat2 identity(){
        return new Mat2(1,0,0,1);
    }

    public FloatBuffer getValues(){
        FloatBuffer b = FloatBuffer.allocate(4);
        b.put(x1);
        b.put(y1);

        b.put(x2);
        b.put(y2);

        b.flip();

        return b;
    }



}
