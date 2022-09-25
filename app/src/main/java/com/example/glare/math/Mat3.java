package com.example.glare.math;

import java.nio.FloatBuffer;

public class Mat3 {
    float x1,x2,x3,y1,y2,y3,z1,z2,z3;

    public Mat3(){
        x1 = 1;
        x2 = x3 = y1 = 0;
        y2 = 1;
        y3 = z1 = z2 = 0;
        z3 = 1;
    }

    public Mat3(float value){
        x1 = value;
        x2 = x3 = y1 = 0;
        y2 = value;
        y3 = z1 = z2 = 0;
        z3 = value;
    }

    public Mat3(Mat3 mat){
        x1 = mat.x1;
        x2 = mat.x2;
        x3 = mat.x3;

        y1 = mat.y1;
        y2 = mat.y2;
        y3 = mat.y3;

        z1 = mat.z1;
        z2 = mat.z2;
        z3 = mat.z3;

    }

    public Mat3 (float x1, float x2, float x3, float y1, float y2, float y3, float z1, float z2, float z3){
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;

        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;

        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
    }

    public Mat3 add(Mat3 mat){
        x1 += mat.x1;
        x2 += mat.x2;
        x3 += mat.x3;

        y1 += mat.y1;
        y2 += mat.y2;
        y3 += mat.y3;

        z1 += mat.z1;
        z2 += mat.z2;
        z3 += mat.z3;
        return this;
    }

    public static Mat3 add(Mat3 mat1, Mat3 mat2){
        Mat3 result = new Mat3();

        result.x1 = mat1.x1 + mat2.x1;
        result.x2 = mat1.x2 + mat2.x2;
        result.x3 = mat1.x3 + mat2.x3;

        result.y1 = mat1.y1 + mat2.y1;
        result.y2 = mat1.y2 + mat2.y2;
        result.y3 = mat1.y3 + mat2.y3;

        result.z1 = mat1.z1 + mat2.z1;
        result.z2 = mat1.z2 + mat2.z2;
        result.z3 = mat1.z3 + mat2.z3;

        return result;
    }

    public Mat3 subtract(Mat3 mat){
        x1 -= mat.x1;
        x2 -= mat.x2;
        x3 -= mat.x3;

        y1 -= mat.y1;
        y2 -= mat.y2;
        y3 -= mat.y3;

        z1 -= mat.z1;
        z2 -= mat.z2;
        z3 -= mat.z3;
        return this;
    }

    public static Mat3 subtract(Mat3 mat1, Mat3 mat2){
        Mat3 result = new Mat3();

        result.x1 = mat1.x1 - mat2.x1;
        result.x2 = mat1.x2 - mat2.x2;
        result.x3 = mat1.x3 - mat2.x3;

        result.y1 = mat1.y1 - mat2.y1;
        result.y2 = mat1.y2 - mat2.y2;
        result.y3 = mat1.y3 - mat2.y3;

        result.z1 = mat1.z1 - mat2.z1;
        result.z2 = mat1.z2 - mat2.z2;
        result.z3 = mat1.z3 - mat2.z3;

        return result;
    }

    public Mat3 multiply(float scalar){
        x1 *= scalar;
        x2 *= scalar;
        x3 *= scalar;

        y1 *= scalar;
        y2 *= scalar;
        y3 *= scalar;

        z1 *= scalar;
        z2 *= scalar;
        z3 *= scalar;
        return this;
    }

    public static Mat3 add(Mat3 mat, float scalar){
        Mat3 result = new Mat3();

        result.x1 = mat.x1 * scalar;
        result.x2 = mat.x2 * scalar;
        result.x3 = mat.x3 * scalar;

        result.y1 = mat.y1 * scalar;
        result.y2 = mat.y2 * scalar;
        result.y3 = mat.y3 * scalar;

        result.z1 = mat.z1 * scalar;
        result.z2 = mat.z2 * scalar;
        result.z3 = mat.z3 * scalar;

        return result;
    }

    public Mat3 multiply(Mat3 mat){
        x1 = x1 * mat.x1 + x2 * mat.y1 + x3 * mat.z1;
        x2 = x1 * mat.x2 + x2 * mat.y2 + x3 * mat.z2;
        x3 = x1 * mat.x3 + x2 * mat.y3 * x3 * mat.z3;

        y1 = y1 * mat.x1 + y2 * mat.y1 + y3 * mat.z1;
        y2 = y1 * mat.x2 + y2 * mat.y2 + y3 * mat.z2;
        y3 = y1 * mat.x3 + y2 * mat.y3 * y3 * mat.z3;

        z1 = z1 * mat.x1 + z2 * mat.y1 + z3 * mat.z1;
        z2 = x1 * mat.x2 + z2 * mat.y2 + z3 * mat.z2;
        z3 = x1 * mat.x3 + z2 * mat.y3 * z3 * mat.z3;
        return this;
    }

    public static Mat3 multiply(Mat3 mat1, Mat3 mat2){
        Mat3 result = new Mat3();

        result.x1 = mat1.x1 * mat2.x1 + mat1.x2 * mat2.y1 + mat1.x3 * mat2.z1;
        result.x2 = mat1.x1 * mat2.x2 + mat1.x2 * mat2.y2 + mat1.x3 * mat2.z2;
        result.x3 = mat1.x1 * mat2.x3 + mat1.x2 * mat2.y3 * mat1.x3 * mat2.z3;

        result.y1 = mat1.y1 * mat2.x1 + mat1.y2 * mat2.y1 + mat1.y3 * mat2.z1;
        result.y2 = mat1.y1 * mat2.x2 + mat1.y2 * mat2.y2 + mat1.y3 * mat2.z2;
        result.y3 = mat1.y1 * mat2.x3 + mat1.y2 * mat2.y3 * mat1.y3 * mat2.z3;

        result.z1 = mat1.z1 * mat2.x1 + mat1.z2 * mat2.y1 + mat1.z3 * mat2.z1;
        result.z2 = mat1.x1 * mat2.x2 + mat1.z2 * mat2.y2 + mat1.z3 * mat2.z2;
        result.z3 = mat1.x1 * mat2.x3 + mat1.z2 * mat2.y3 * mat1.z3 * mat2.z3;

        return result;
    }

    public Mat3 transpose(){
        Mat3 mat = new Mat3(this);

        x1 = mat.x1;
        x2 = mat.y1;
        x3 = mat.z1;

        y1 = mat.x2;
        y2 = mat.y2;
        y3 = mat.z2;

        z1 = mat.x3;
        z2 = mat.y3;
        z3 = mat.z3;

        return this;
    }

    public Mat3 transpose(Mat3 mat){
        Mat3 result = new Mat3();

        result.x1 = mat.x1;
        result.x2 = mat.y1;
        result.x3 = mat.z1;

        result.y1 = mat.x2;
        result.y2 = mat.y2;
        result.y3 = mat.z2;

        result.z1 = mat.x3;
        result.z2 = mat.y3;
        result.z3 = mat.z3;

        return result;
    }

    public static Mat3 rotation(Vec3 axis, float a){
        axis.normalize();
        Mat3 mat = new Mat3();
        float cosA = (float) Math.cos(a);
        float sinA = (float) Math.sin(a);
        mat.x1 = axis.x * axis.x * (1 - cosA) + cosA;
        mat.x2 = axis.y * axis.x * (1 - cosA) - axis.z * sinA;
        mat.x3 = axis.z * axis.x * (1 - cosA) + axis.y * sinA;

        mat.y1 = axis.x * axis.y * (1 - cosA) + axis.z * sinA;
        mat.y2 = axis.y * axis.y * (1 - cosA) + cosA;
        mat.y3 = axis.z * axis.y * (1 - cosA) - axis.x * sinA;

        mat.z1 = axis.x * axis.z * (1 - cosA) - axis.y * sinA;
        mat.z2 = axis.y * axis.z * (1 - cosA) + axis.x * sinA;
        mat.z3 = axis.z * axis.z * (1 - cosA) + cosA;
        return mat;
    }

    public FloatBuffer getValues(){
        FloatBuffer b = FloatBuffer.allocate(9);
        b.put(x1);
        b.put(y1);
        b.put(z1);

        b.put(x2);
        b.put(y2);
        b.put(z2);

        b.put(x3);
        b.put(y3);
        b.put(z3);

        b.flip();


        return b;
    }




}
