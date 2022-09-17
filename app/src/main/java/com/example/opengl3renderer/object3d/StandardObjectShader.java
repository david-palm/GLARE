package com.example.opengl3renderer.object3d;

import android.content.Context;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Uniform;

public class StandardObjectShader extends ObjectShader {
    Uniform textured;
    Uniform color;
    Uniform albedo;
    Uniform normal;
    Uniform normalIntensity;
    Uniform roughness;
    Uniform roughnessIntensity;
    Uniform pointLightPositions;
    Uniform pointLightColors;
    Uniform ambientLight;
    public StandardObjectShader(Context context) {
        super(context, "shaders/StandardObjectShader.vs", "shaders/StandardObjectShader.fs");
        textured = new Uniform("uTextured", shaderProgramId);
        color = new Uniform("uColor", shaderProgramId);
        albedo = new Uniform("uAlbedo", 0, shaderProgramId);
        normal = new Uniform("uNormal", 1, shaderProgramId);
        normalIntensity = new Uniform("uNormalIntensity", shaderProgramId);
        roughness = new Uniform("uRoughness", 2, shaderProgramId);
        roughnessIntensity = new Uniform("uRoughnessIntensity", shaderProgramId);
        pointLightPositions = new Uniform("uPointLightPositions", new Mat4(), shaderProgramId);
        pointLightColors = new Uniform("uPointLightColors", new Mat4(), shaderProgramId);
        ambientLight = new Uniform("uAmbientLight", new Vec3(), shaderProgramId);
    }

    public void setTextured(boolean isTextured) {
        textured.setObject(isTextured);
        textured.sendToShader();
    }
    public void setColor(Vec3 color){
        this.color.setObject(color);
        this.color.sendToShader();
    }
    public void setColor(Vec4 color){
        this.color.setObject(color);
        this.color.sendToShader();
    }
    public void setAlbedoLocation(int location){
        albedo.setObject(location);
        albedo.sendToShader();
    }
    public void setNormalLocation(int location){
        normal.setObject(location);
        normal.sendToShader();
    }
    public void setRoughnessLocation(int location){
        roughness.setObject(location);
        roughness.sendToShader();
    }
    public void setNormalIntensity(float intensity){
        normalIntensity.setObject(intensity);
        normalIntensity.sendToShader();
    }
    public void setRoughnessIntensity(float intensity){
        roughnessIntensity.setObject(intensity);
        roughnessIntensity.sendToShader();
    }
    public void setPointLightPositions(Vec3[] pointLightPositions){
        this.pointLightPositions.setObject(pointLightPositions);
        this.pointLightPositions.sendToShader();
    }
    public void setPointLightColors(Vec3[] pointLightColors){
        this.pointLightColors.setObject(pointLightColors);
        this.pointLightColors.sendToShader();
    }
    public void setAmbientLight(Vec3 ambientLight){
        this.ambientLight.setObject(ambientLight);
        this.ambientLight.sendToShader();
    }
}
