package com.example.opengl3renderer.math;

import java.nio.FloatBuffer;

public class Mat4 {
    public float x1,x2,x3,x4,y1,y2,y3,y4,z1,z2,z3,z4,w1,w2,w3,w4;

    public Mat4(){
        x1 = 1;
        x2 = x3 = x4 = y1 = 0;
        y2 = 1;
        y3 = y4 = z1 = z2 = 0;
        z3 = 1;
        z4 = w1 = w2 = w3 = 0;
        w4 = 1;
    }

    public Mat4(float value){
        x1 = value;
        x2 = x3 = x4 = y1 = 0;
        y2 = value;
        y3 = y4 = z1 = z2 = 0;
        z3 = value;
        z4 = w1 = w2 = w3 = 0;
        w4 = value;
    }

    public Mat4(Mat3 mat){
        x1 = mat.x1;
        x2 = mat.x2;
        x3 = mat.x3;
        x4 = 0;

        y1 = mat.y1;
        y2 = mat.y2;
        y3 = mat.y3;
        y4 = 0;

        z1 = mat.z1;
        z2 = mat.z2;
        z3 = mat.z3;
        z4 = 0;

        w1 = 0;
        w2 = 0;
        w3 = 0;
        w4 = 1;
    }

    public Mat4(Mat4 mat){
        x1 = mat.x1;
        x2 = mat.x2;
        x3 = mat.x3;
        x4 = mat.x4;

        y1 = mat.y1;
        y2 = mat.y2;
        y3 = mat.y3;
        y4 = mat.y4;

        z1 = mat.z1;
        z2 = mat.z2;
        z3 = mat.z3;
        z4 = mat.z4;

        w1 = mat.w1;
        w2 = mat.w2;
        w3 = mat.w3;
        w4 = mat.w4;
    }

    Mat4(Vec3 position, Vec3 scale){
        x1 = scale.x;
        x2 = 0.0f;
        x3 = 0.0f;
        x4 = position.x;

        y1 = 0.0f;
        y2 = scale.y;
        y3 = 0.0f;
        y4 = position.y;

        z1 = 0.0f;
        z2 = 0.0f;
        z3 = scale.z;
        z4 = position.z;

        w1 = 0.0f;
        w2 = 0.0f;
        w3 = 0.0f;
        w4 = 1.0f;
    }

    public Mat4(float x1, float x2, float x3, float x4, float y1, float y2, float y3, float y4, float z1, float z2, float z3, float z4, float w1, float w2, float w3, float w4){
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;

        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;

        this.z1 = z1;
        this.z2 = z2;
        this.z3 = z3;
        this.z4 = z4;

        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
    }

    Mat4(Vec3 position, Vec3 scale, Vec4 rotation){
        Mat4 positionScaleMatrix = new Mat4(position, scale);
        Mat4 rotationMatrix = Mat4.rotation(rotation.getVec3(), rotation.w);
        Mat4 mat = multiply(positionScaleMatrix, rotationMatrix);
        x1 = mat.x1;
        x2 = mat.x2;
        x3 = mat.x3;
        x4 = mat.x4;

        y1 = mat.y1;
        y2 = mat.y2;
        y3 = mat.y3;
        y4 = mat.y4;

        z1 = mat.z1;
        z2 = mat.z2;
        z3 = mat.z3;
        z4 = mat.z4;

        w1 = mat.w1;
        w2 = mat.w2;
        w3 = mat.w3;
        w4 = mat.w4;
    }

    public Mat4 add(Mat4 mat){
        x1 += mat.x1;
        x2 += mat.x2;
        x3 += mat.x3;
        x4 += mat.x4;

        y1 += mat.y1;
        y2 += mat.y2;
        y3 += mat.y3;
        y4 += mat.y4;

        z1 += mat.z1;
        z2 += mat.z2;
        z3 += mat.z3;
        z4 += mat.z4;

        w1 += mat.w1;
        w2 += mat.w2;
        w3 += mat.w3;
        w4 += mat.w4;
        return this;
    }

    public static Mat4 add(Mat4 mat1, Mat4 mat2){
        Mat4 mat = new Mat4();

        mat.x1 = mat1.x1 + mat2.x1;
        mat.x2 = mat1.x2 + mat2.x2;
        mat.x3 = mat1.x3 + mat2.x3;
        mat.x4 = mat1.x4 + mat2.x4;

        mat.y1 = mat1.y1 + mat2.y1;
        mat.y2 = mat1.y2 + mat2.y2;
        mat.y3 = mat1.y3 + mat2.y3;
        mat.y4 = mat1.y4 + mat2.y4;

        mat.z1 = mat1.z1 + mat2.z1;
        mat.z2 = mat1.z2 + mat2.z2;
        mat.z3 = mat1.z3 + mat2.z3;
        mat.z4 = mat1.z4 + mat2.z4;

        mat.w1 = mat1.w1 + mat2.w1;
        mat.w2 = mat1.w2 + mat2.w2;
        mat.w3 = mat1.w3 + mat2.w3;
        mat.w4 = mat1.w4 + mat2.w4;

        return mat;
    }

    Mat4 subtract(Mat4 mat){
        x1 -= mat.x1;
        x2 -= mat.x2;
        x3 -= mat.x3;
        x4 -= mat.x4;

        y1 -= mat.y1;
        y2 -= mat.y2;
        y3 -= mat.y3;
        y4 -= mat.y4;

        z1 -= mat.z1;
        z2 -= mat.z2;
        z3 -= mat.z3;
        z4 -= mat.z4;

        w1 -= mat.w1;
        w2 -= mat.w2;
        w3 -= mat.w3;
        w4 -= mat.w4;
        return this;
    }

    public static Mat4 subtract(Mat4 mat1, Mat4 mat2){
        Mat4 mat = new Mat4();

        mat.x1 = mat1.x1 - mat2.x1;
        mat.x2 = mat1.x2 - mat2.x2;
        mat.x3 = mat1.x3 - mat2.x3;
        mat.x4 = mat1.x4 - mat2.x4;

        mat.y1 = mat1.y1 - mat2.y1;
        mat.y2 = mat1.y2 - mat2.y2;
        mat.y3 = mat1.y3 - mat2.y3;
        mat.y4 = mat1.y4 - mat2.y4;

        mat.z1 = mat1.z1 - mat2.z1;
        mat.z2 = mat1.z2 - mat2.z2;
        mat.z3 = mat1.z3 - mat2.z3;
        mat.z4 = mat1.z4 - mat2.z4;

        mat.w1 = mat1.w1 - mat2.w1;
        mat.w2 = mat1.w2 - mat2.w2;
        mat.w3 = mat1.w3 - mat2.w3;
        mat.w4 = mat1.w4 - mat2.w4;

        return mat;
    }

    public Mat4 multiply(float scalar){
        x1 *= scalar;
        x2 *= scalar;
        x3 *= scalar;
        x4 *= scalar;

        y1 *= scalar;
        y2 *= scalar;
        y3 *= scalar;
        y4 *= scalar;

        z1 *= scalar;
        z2 *= scalar;
        z3 *= scalar;
        z4 *= scalar;

        w1 *= scalar;
        w2 *= scalar;
        w3 *= scalar;
        w4 *= scalar;
        return this;
    }

    public static Mat4 multiply(Mat4 mat, float scalar){
        Mat4 result = new Mat4(mat);
        result.x1 *= scalar;
        result.x2 *= scalar;
        result.x3 *= scalar;
        result.x4 *= scalar;

        result.y1 *= scalar;
        result.y2 *= scalar;
        result.y3 *= scalar;
        result.y4 *= scalar;

        result.z1 *= scalar;
        result.z2 *= scalar;
        result.z3 *= scalar;
        result.z4 *= scalar;

        result.w1 *= scalar;
        result.w2 *= scalar;
        result.w3 *= scalar;
        result.w4 *= scalar;

        return result;
    }

    public Mat4 multiply(Mat4 mat){
        Mat4 result = new Mat4();
        result.x1 = x1 * mat.x1 + x2 * mat.y1 + x3 * mat.z1 + x4 * mat.w1;
        result.x2 = x1 * mat.x2 + x2 * mat.y2 + x3 * mat.z2 + x4 * mat.w2;
        result.x3 = x1 * mat.x3 + x2 * mat.y3 + x3 * mat.z3 + x4 * mat.w3;
        result.x4 = x1 * mat.x4 + x2 * mat.y4 + x3 * mat.z4 + x4 * mat.w4;

        result.y1 = y1 * mat.x1 + y2 * mat.y1 + y3 * mat.z1 + y4 * mat.w1;
        result.y2 = y1 * mat.x2 + y2 * mat.y2 + y3 * mat.z2 + y4 * mat.w2;
        result.y3 = y1 * mat.x3 + y2 * mat.y3 + y3 * mat.z3 + y4 * mat.w3;
        result.y4 = y1 * mat.x4 + y2 * mat.y4 + y3 * mat.z4 + y4 * mat.w4;

        result.z1 = z1 * mat.x1 + z2 * mat.y1 + z3 * mat.z1 + z4 * mat.w1;
        result.z2 = z1 * mat.x2 + z2 * mat.y2 + z3 * mat.z2 + z4 * mat.w2;
        result.z3 = z1 * mat.x3 + z2 * mat.y3 + z3 * mat.z3 + z4 * mat.w3;
        result.z4 = z1 * mat.x4 + z2 * mat.y4 + z3 * mat.z4 + z4 * mat.w4;

        result.w1 = w1 * mat.x1 + w2 * mat.y1 + w3 * mat.z1 + w4 * mat.w1;
        result.w2 = w1 * mat.x2 + w2 * mat.y2 + w3 * mat.z2 + w4 * mat.w2;
        result.w3 = w1 * mat.x3 + w2 * mat.y3 + w3 * mat.z3 + w4 * mat.w3;
        result.w4 = w1 * mat.x4 + w2 * mat.y4 + w3 * mat.z4 + w4 * mat.w4;

        return result;
    }

    public static Mat4 multiply(Mat4 mat1, Mat4 mat2){
        Mat4 result = new Mat4();
        result.x1 = mat1.x1 * mat2.x1 + mat1.x2 * mat2.y1 + mat1.x3 * mat2.z1 + mat1.x4 * mat2.w1;
        result.x2 = mat1.x1 * mat2.x2 + mat1.x2 * mat2.y2 + mat1.x3 * mat2.z2 + mat1.x4 * mat2.w2;
        result.x3 = mat1.x1 * mat2.x3 + mat1.x2 * mat2.y3 + mat1.x3 * mat2.z3 + mat1.x4 * mat2.w3;
        result.x4 = mat1.x1 * mat2.x4 + mat1.x2 * mat2.y4 + mat1.x3 * mat2.z4 + mat1.x4 * mat2.w4;

        result.y1 = mat1.y1 * mat2.x1 + mat1.y2 * mat2.y1 + mat1.y3 * mat2.z1 + mat1.y4 * mat2.w1;
        result.y2 = mat1.y1 * mat2.x2 + mat1.y2 * mat2.y2 + mat1.y3 * mat2.z2 + mat1.y4 * mat2.w2;
        result.y3 = mat1.y1 * mat2.x3 + mat1.y2 * mat2.y3 + mat1.y3 * mat2.z3 + mat1.y4 * mat2.w3;
        result.y4 = mat1.y1 * mat2.x4 + mat1.y2 * mat2.y4 + mat1.y3 * mat2.z4 + mat1.y4 * mat2.w4;

        result.z1 = mat1.z1 * mat2.x1 + mat1.z2 * mat2.y1 + mat1.z3 * mat2.z1 + mat1.z4 * mat2.w1;
        result.z2 = mat1.z1 * mat2.x2 + mat1.z2 * mat2.y2 + mat1.z3 * mat2.z2 + mat1.z4 * mat2.w2;
        result.z3 = mat1.z1 * mat2.x3 + mat1.z2 * mat2.y3 + mat1.z3 * mat2.z3 + mat1.z4 * mat2.w3;
        result.z4 = mat1.z1 * mat2.x4 + mat1.z2 * mat2.y4 + mat1.z3 * mat2.z4 + mat1.z4 * mat2.w4;

        result.w1 = mat1.w1 * mat2.x1 + mat1.w2 * mat2.y1 + mat1.w3 * mat2.z1 + mat1.w4 * mat2.w1;
        result.w2 = mat1.w1 * mat2.x2 + mat1.w2 * mat2.y2 + mat1.w3 * mat2.z2 + mat1.w4 * mat2.w2;
        result.w3 = mat1.w1 * mat2.x3 + mat1.w2 * mat2.y3 + mat1.w3 * mat2.z3 + mat1.w4 * mat2.w3;
        result.w4 = mat1.w1 * mat2.x4 + mat1.w2 * mat2.y4 + mat1.w3 * mat2.z4 + mat1.w4 * mat2.w4;

        return result;
    }

    public static Mat4 translation(Vec3 vec){
        return new Mat4(1.0f, 0.0f, 0.0f, vec.x, 0.0f, 1.0f, 0.0f, vec.y, 0.0f, 0.0f, 1.0f, vec.z, 0.0f, 0.0f, 0.0f, 1.0f);

    }

    public static Mat4 rotation(Vec3 axis, float alpha){
        return new Mat4(Mat3.rotation(axis, alpha));
    }

    public static Mat4 scale(Vec3 scale){
        Mat4 mat = new Mat4();

        mat.x1 = scale.x;
        mat.y2 = scale.y;
        mat.z3 = scale.z;

        return mat;
    }

    public static Mat4 lookAt(Vec3 position, Vec3 target, Vec3 up){
        up.normalize();
        Vec3 direction = target.subtract(position).normalize();
        Vec3 right = up.cross(direction).normalize();
        return new Mat4(right.x, right.y, right.z, position.negate().dot(right),
                    up.x, up.y, up.z, position.dot(up),
                    direction.x, direction.y, direction.z, position.dot(direction),
                    0, 0, 0, 1);
    }

    public static Mat4 perspective(float left, float right, float top, float bottom, float near, float far){
        return new Mat4((2 * near)/(right - left), 0, (right + left)/(right - left), 0,
                    0, (2 * near)/(top - bottom), (top + bottom)/(top - bottom), 0,
                    0, 0, -(far + near)/(far - near), -(2 * far * near)/(far -near),
                    0, 0, -1.0f, 0);

    }

    public static Mat4 perspective(float fov, float aspectRatio, float near, float far){
        return new Mat4((float)(1 / (aspectRatio * Math.tan(fov / 2))), 0, 0, 0,
                        0, 1 / ((float)Math.tan(fov / 2)), 0, 0,
                        0, 0, -(far + near)/(far - near), -(2 * far * near)/(far - near),
                        0, 0, -1, 0);
    }

    public String toString(){
        return (x1 + " " + x2 + " " + x3 + " " + x4 +"\n"
                + y1 + " " + y2 + " " + y3 + " " + y4 +"\n"
                + z1 + " " + z2 + " " + z3 + " " + z4 +"\n"
                + w1 + " " + w2 + " " + w3 + " " + w4 +"\n");
    }


    public FloatBuffer getValues(){
        FloatBuffer b = FloatBuffer.allocate(16);
        b.put(x1);
        b.put(y1);
        b.put(z1);
        b.put(w1);

        b.put(x2);
        b.put(y2);
        b.put(z2);
        b.put(w2);

        b.put(x3);
        b.put(y3);
        b.put(z3);
        b.put(w3);

        b.put(x4);
        b.put(y4);
        b.put(z4);
        b.put(w4);

        b.flip();


        return b;
    }
}
