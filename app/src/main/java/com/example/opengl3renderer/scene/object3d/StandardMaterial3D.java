package com.example.opengl3renderer.scene.object3d;

import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Texture;

public class StandardMaterial3D extends Material3D {
    StandardObject3DShader shader;

    boolean textured;
    Vec4 color;
    Texture albedo;
    Texture normal;
    float normalIntensity;
    Texture roughness;
    float roughnessIntensity;

    // Creates a light grey material
    public StandardMaterial3D(StandardObject3DShader shader){
        this.shader = shader;

        textured = false;
        color = new Vec4(0.8f, 0.8f, 0.8f, 1.0f);
        albedo = null;
        normal = null;
        normalIntensity = 0.0f;
        roughness = null;
        roughnessIntensity = 0.0f;

        // Initializing shader values
        // Setting up texture locations. Do not need to be changed after initialization
        this.shader.bind();
        this.shader.setAlbedoLocation(0);
        this.shader.setNormalLocation(1);
        this.shader.setRoughnessLocation(2);

        // Setting up material values. Only change if material changes
        this.shader.setTextured(textured);
        this.shader.setColor(color);
        this.shader.setNormalIntensity(normalIntensity);
        this.shader.setRoughnessIntensity(roughnessIntensity);
    }

    public void setShader(StandardObject3DShader shader){
        this.shader = shader;
    }
    public StandardObject3DShader getShader(){
        return shader;
    }
    public boolean isTextured(){
        return textured;
    }
    public void setTextured(boolean textured){
        this.textured = textured;
    }
    public Vec4 getColor(){
        return color;
    }
    public void setColor(Vec4 color){
        this.color = color;
    }
    public Texture getAlbedo(){
        return albedo;
    }
    public void setAlbedo(Texture albedo){
        this.albedo = albedo;
    }
    public Texture getNormal(){
        return normal;
    }
    public void setNormal(Texture normal){
        this.normal = normal;
    }
    public float getNormalIntensity(){
        return normalIntensity;
    }
    public void setNormalIntensity(float normalIntensity){
        this.normalIntensity = normalIntensity;
    }
    public Texture getRoughness(){
        return roughness;
    }
    public void setRoughness(Texture roughness){
        this.roughness = roughness;
    }
    public float getRoughnessIntensity(){
        return roughnessIntensity;
    }
    public void setRoughnessIntensity(float roughnessIntensity){
        this.roughnessIntensity = roughnessIntensity;
    }
}
