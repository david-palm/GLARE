package com.example.opengl3renderer.ui.object2d;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.renderer.Mesh;

public abstract class Object2D {
    protected Mesh mesh;
    Material2D material;

    protected Vec2 position;
    protected Vec2 scale;
    protected float rotation;
    protected Mat4 model;

    public Object2D(Mesh mesh, Material2D material, Vec2 position, Vec2 scale, float rotation){
        this.mesh = mesh;
        this.material = material;
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
        model = new Mat4();
        model.x4 = position.x;
        model.y4 = position.y;
        model.x1 = scale.x;
        model.y2 = scale.y * (float) Math.cos(rotation);
        model.z3 =  (float) -Math.sin(rotation);
        model.z2 = (float) Math.sin(rotation);
        model.z3 = (float) Math.cos(rotation);
    }

    public Object2D(Mesh mesh, Material2D material){
        this(mesh, material, new Vec2(), new Vec2(1.0f), 0.0f);
    }

    public void onRender(){
        // Binding shader and make OpenGL calls
        material.getShader().bind();
        // Sending matrices to shader
        material.getShader().setModel(model);
        mesh.onRender();
    }

    public Mesh getMesh(){
        return mesh;
    }

    public void setMesh(Mesh mesh){
        this.mesh = mesh;
    }

    public Material2D getMaterial(){
        return material;
    }

    public void setMaterial(Material2D material){
        this.material = material;
    }

    public Vec2 getPosition(){
        return position;
    }

    public void setPosition(Vec2 position){
        this.position = position;
    }

    public Vec2 getScale(){
        return scale;
    }

    public void setScale(Vec2 scale){
        this.scale = scale;
        model.x1 = scale.x;
        model.y2 = scale.y * (float) Math.cos(rotation);
    }

    public float getRotation(){
        return rotation;
    }

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public abstract boolean isInside(float x, float y);

    public Mat4 getModel(){
        return model;
    }
}
