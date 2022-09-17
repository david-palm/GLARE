package com.example.opengl3renderer.object3d;

public class Material3D {
    ObjectShader shader;

    public Material3D(){

    }

    public Material3D(ObjectShader shader){
        this.shader = shader;
    }

    protected void setShader(ObjectShader shader){
        this.shader = shader;
    }
    protected ObjectShader getShader(){
        return shader;
    }
}
