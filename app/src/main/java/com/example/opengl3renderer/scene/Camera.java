package com.example.opengl3renderer.scene;

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
        view = new Mat4();
        view.y4 = 0.25f;
        view.z4 = -5.0f;
        projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
    }
    public float getFov(){
        return fov;
    }
    public void setFov(float fov){
        this.fov = fov;
        projection = projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
    }
    public Vec3 getPosition(){
        return position;
    }

    public Mat4 getView(){
        return view;
    }

    public Mat4 getProjection(){
        return projection;
    }
}
