package com.example.opengl3renderer.object3d;

import android.util.Log;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;

public class Camera {
    Vec3 position;
    Mat4 view;
    Mat4 projection;
    float aspectRatio;
    float fov;
    float near = 0.1f;
    float far = 100.0f;

    public Camera (Vec3 position, float fov, float aspectRatio){
        this.position = position;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        view = Mat4.lookAt(position, new Vec3(0.0f), new Vec3(0,0.1f,0));
        Log.d("Camera", view.x1 + " " + view.x2 + " " + view.x3 + " " + view.x4 );
        Log.d("Camera", view.y1 + " " + view.y2 + " " + view.y3 + " " + view.y4 );
        Log.d("Camera", view.z1 + " " + view.z2 + " " + view.z3 + " " + view.z4 );
        Log.d("Camera", view.w1 + " " + view.w2 + " " + view.w3 + " " + view.w4 );
        view = new Mat4();
        view.y4 = 0.25f;
        view.z4 = -5.0f;
        projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
    }
    public float getFov(){
        return fov;
    }
    public void setFov(float fov) {
        this.fov = fov;
        projection = projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
    }
    public Vec3 getPosition() {
        return position;
    }

    public Mat4 getView() {
        return view;
    }

    public Mat4 getProjection() {
        return projection;
    }
}
