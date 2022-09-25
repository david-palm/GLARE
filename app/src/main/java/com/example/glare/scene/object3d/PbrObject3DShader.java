package com.example.glare.scene.object3d;

import android.content.Context;

import com.example.glare.math.Mat4;
import com.example.glare.math.Vec3;
import com.example.glare.math.Vec4;
import com.example.glare.renderer.Uniform;
import com.example.glare.renderer.Texture;

public class PbrObject3DShader extends Object3DShader {
    Uniform color;
    Uniform colorIntensity;
    Uniform normal;
    Uniform normalIntensity;
    Uniform metallic;
    Uniform metallicIntensity;
    Uniform roughness;
    Uniform roughnessIntensity;
    Uniform ao;
    Uniform aoIntensity;

    Uniform model;
    Uniform view;
    Uniform projection;

    Uniform lightPositions;
    Uniform lightColors;
    public PbrObject3DShader(Context context) {
        super(context, "shaders/PbrObjectShader.vs", "shaders/PbrObjectShader.fs");
        color = new Uniform("uAlbedo", new Vec4(1.0f, 1.0f, 1.0f, 1.0f), shaderProgramId);
        colorIntensity = new Uniform("uColorIntensity", 1.0f, shaderProgramId);
        normal = new Uniform("uNormal", shaderProgramId);
        normalIntensity = new Uniform("uNormalIntensity", 0.0f, shaderProgramId);
        metallic = new Uniform("uMetallic", shaderProgramId);
        metallicIntensity = new Uniform("uMetallicIntensity", 0.0f, shaderProgramId);
        roughness = new Uniform("uRoughness", shaderProgramId);
        roughnessIntensity = new Uniform("uRoughnessIntensity", 1.0f, shaderProgramId);
        ao = new Uniform("uAo", shaderProgramId);
        aoIntensity = new Uniform("uAoIntensity", 0.0f, shaderProgramId);

        model = new Uniform("uModel", new Mat4(), shaderProgramId);
        view = new Uniform("uView", new Mat4(), shaderProgramId);
        projection = new Uniform("uProjection", new Mat4(), shaderProgramId);

        lightPositions = new Uniform("uLightPositions", new Mat4(), shaderProgramId);
        lightColors = new Uniform("uLightColors", new Mat4(), shaderProgramId);
    }

    public void setColor(Vec3 color){
        this.color.setObject(new Vec4(color, 1.0f));
        this.color.sendToShader();
    }
    public void setColor(Vec4 color){
        this.color.setObject(color);
        this.color.sendToShader();
    }
    public void setColor(Texture texture){
        color.setObject(texture);
        color.sendToShader();
    }
    public void setColorIntensity(float intensity){
        colorIntensity.setObject(intensity);
        colorIntensity.sendToShader();
    }
    public void setMetallic(Texture texture){
        metallic.setObject(texture);
        metallic.sendToShader();
    }
    public void setMetallicIntensity(float intensity){
        metallicIntensity.setObject(intensity);
        metallicIntensity.sendToShader();
    }
    public void setNormal(Texture texture){
        normal.setObject(texture);
        normal.sendToShader();
    }
    public void setNormalIntensity(float intensity){
        normalIntensity.setObject(intensity);
        normalIntensity.sendToShader();
    }
    public void setRoughness(Texture texture){
        roughness.setObject(texture);
        roughness.sendToShader();
    }
    public void setRoughnessIntensity(float intensity){
        roughnessIntensity.setObject(intensity);
        roughnessIntensity.sendToShader();
    }
    public void setAo(Texture texture){
        ao.setObject(texture);
        ao.sendToShader();
    }
    public void setAoIntensity(float intensity){
        aoIntensity.setObject(intensity);
        aoIntensity.sendToShader();
    }
    public void setModel(Mat4 model){
        this.model.setObject(model);
        this.model.sendToShader();
    }
    public void setView(Mat4 view){
        this.view.setObject(view);
        this.view.sendToShader();
    }
    public void setProjection(Mat4 projection){
        this.projection.setObject(projection);
        this.projection.sendToShader();
    }
    public void setLightPositions(Vec3[] lightPositions){
        this.lightPositions.setObject(lightPositions);
        this.lightPositions.sendToShader();
    }
    public void setLightColors(Vec3[] lightColors){
        this.lightColors.setObject(lightColors);
        this.lightColors.sendToShader();
    }
}
