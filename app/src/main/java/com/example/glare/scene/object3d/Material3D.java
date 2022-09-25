package com.example.glare.scene.object3d;

public class Material3D {
    Object3DShader shader;

    public Material3D(){

    }

    public Material3D(Object3DShader shader){
        this.shader = shader;
    }

    protected void setShader(Object3DShader shader){
        this.shader = shader;
    }

    protected Object3DShader getShader(){
        return shader;
    }
}
