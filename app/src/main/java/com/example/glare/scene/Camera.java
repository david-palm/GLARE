package com.example.glare.scene;

import com.example.glare.inertia.ExponentialFunction;
import com.example.glare.inertia.Inertia;
import com.example.glare.math.Mat4;
import com.example.glare.math.Vec3;

public class Camera {
    Vec3 position;
    Mat4 view;
    Mat4 projection;
    float aspectRatio;
    float fov;
    float near = 0.1f;
    float far = 100.0f;
    Inertia inertia;
    float fovVelocity;

    public Camera (Vec3 position, float fov, float aspectRatio){
        this.position = position;
        this.fov = fov;
        this.aspectRatio = aspectRatio;
        view = Mat4.lookAt(position, new Vec3(0.0f), new Vec3(0,0.1f,0));
        view = new Mat4();
        view.y4 = 0.25f;
        view.z4 = -5.0f;
        projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
        inertia = new Inertia();
        fovVelocity = 0.0f;
    }
    public float getFov(){
        return fov;
    }
    public void setFov(float fov){
        fov = fov < 5.0f ? 5.0f : fov;
        fov = fov > 120.0f ? 120.0f : fov;
        this.fov = fov;
        projection =  Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
    }
    public void setAspectRatio(float aspectRatio){
        this.aspectRatio = aspectRatio;
        projection = Mat4.perspective((float)Math.toRadians(fov), aspectRatio, 0.1f, 100);
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

    public void setFovVelocity(float fovVelocity){
        this.fovVelocity = fovVelocity;
    }

    public void onUpdate(){
        //Log.d("Fov Velocity", "" + fovVelocity + ", Fov: " + fov);
        fov += 2 * fovVelocity;
        fovVelocity *= inertia.getValue();
        setFov(fov);
    }

    public void startDeceleration(){
        long duration = (long) (fovVelocity * 5000);
        duration = 5000;
        inertia.setDuration(duration);
        ExponentialFunction function = new ExponentialFunction(2, duration);
        inertia.setFunction(function);
        inertia.start();
    }
}
