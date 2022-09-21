package com.example.opengl3renderer.ui.object2d;

public class Material2D {
    Object2DShader shader;

    public Material2D(){

    }

    public Material2D(Object2DShader shader){
        this.shader = shader;
    }

    public Object2DShader getShader(){
        return shader;
    }

    public void setShader(Object2DShader shader){
        this.shader = shader;
    }
}
