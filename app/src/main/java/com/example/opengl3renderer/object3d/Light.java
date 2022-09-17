package com.example.opengl3renderer.object3d;

import com.example.opengl3renderer.math.Vec3;

public class Light {
    Vec3 position;
    Vec3 color;
    public Light(){
        position = new Vec3();
        color = new Vec3(1.0f, 1.0f, 1.0f);
    }

    public Light(Vec3 position){
        this.position = position;
        color = new Vec3(1.0f, 1.0f, 1.0f);
    }

    public Light(Vec3 position, Vec3 color){
        this.position = position;
        this.color = color;
    }

    public Light(Vec3 position, Vec3 color, float intensity){
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
