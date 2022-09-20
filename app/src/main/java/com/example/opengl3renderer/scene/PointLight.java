package com.example.opengl3renderer.scene;

import com.example.opengl3renderer.math.Vec3;

public class PointLight {
    Vec3 position;
    Vec3 color;
    public PointLight(){
        position = new Vec3();
        color = new Vec3(1.0f, 1.0f, 1.0f);
    }

    public PointLight(Vec3 position){
        this.position = position;
        color = new Vec3(1.0f, 1.0f, 1.0f);
    }

    public PointLight(Vec3 position, Vec3 color){
        this.position = position;
        this.color = color;
    }

    public PointLight(Vec3 position, Vec3 color, float intensity){
        this.position = position;
        this.color = color.multiply(intensity);
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getColor() {
        return color;
    }

    public void setColor(Vec3 color) {
        this.color = color;
    }
}
