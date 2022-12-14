package com.example.glare.scene.object3d;

import android.opengl.GLES32;
import android.util.Log;

import com.example.glare.inertia.ExponentialFunction;
import com.example.glare.inertia.Inertia;
import com.example.glare.math.Mat4;
import com.example.glare.math.Vec3;
import com.example.glare.math.Vec4;
import com.example.glare.renderer.Mesh;
import com.example.glare.scene.Scene;


public class Object3D {
    Mesh mesh;
    StandardMaterial3D material;

    Vec3 position;
    Vec3 scale;
    Vec4 rotation;
    Mat4 model;

    Vec3 rotationVelocity;
    boolean rotating;
    Inertia inertia;


    // If no material is given to the constructor, the standard material is assigned to the object.
    public Object3D(Mesh mesh, StandardObject3DShader shader){
        this(mesh, new StandardMaterial3D(shader));
    }

    public Object3D(Mesh mesh, StandardMaterial3D material){
        this.mesh = mesh;
        this.material = material;

        position = new Vec3();
        scale = new Vec3(1.0f);
        rotation = new Vec4();
        model = new Mat4();
        rotationVelocity = new Vec3();

        inertia = new Inertia();

        // Setting up model matrix. Only changes when object moves
        this.material.getShader().setModel(model);
    }

    // Draws object to the screen
    public void onRender(Scene scene){
        // Binding shader and make OpenGL calls
        material.getShader().bind();
        // Sending material values to shader
        material.getShader().setTextured(material.isTextured());
        material.getShader().setColor(material.getColor());
        material.getShader().setNormalIntensity(material.getNormalIntensity());
        material.getShader().setRoughnessIntensity(material.getRoughnessIntensity());
        // Sending matrices to shader
        material.getShader().setModel(model);
        material.getShader().setView(scene.getCamera().getView());
        material.getShader().setProjection(scene.getCamera().getProjection());
        //Sending lights to shader
        material.getShader().setPointLightPositions(scene.getPointLightPositions());
        material.getShader().setPointLightColors(scene.getPointLightColors());
        material.getShader().setAmbientLight(scene.getAmbientLight());

        // Sending textures
        GLES32.glActiveTexture(GLES32.GL_TEXTURE0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, material.getAlbedo().getId());

        GLES32.glActiveTexture(GLES32.GL_TEXTURE1);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, material.getNormal().getId());

        GLES32.glActiveTexture(GLES32.GL_TEXTURE2);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, material.getRoughness().getId());

        mesh.onRender();
        material.getShader().unbind();
    }

    public void onUpdate(){
        rotate(new Vec4(0.0f, 1.0f, 0.0f, rotationVelocity.x));
        rotate(new Vec4(1.0f, 0.0f, 0.0f, rotationVelocity.y));
        rotationVelocity.multiply(inertia.getValue());

    }

    public void startDeceleration(){
        long duration = (long) (Math.pow(10 * rotationVelocity.length(), 1.4) * 800);
        inertia.setDuration(duration);
        inertia.setFunction(new ExponentialFunction(2, duration));
        inertia.start();
    }

    public void rotate(Vec4 rotation){
        model = Mat4.multiply(Mat4.rotation(rotation.getVec3(), rotation.w), model);
        material.getShader().setModel(model);
    }

    public void translate(Vec3 translation){
        position.add(translation);
        model = Mat4.multiply(Mat4.translation(translation), model);
        Log.d("Object3D", "Translated object. Model matrix is now: " + model.toString());
    }
/*
    public void scale(Vec3 scale){
        scale.multiply(scale);
        model.multiply(Mat4.scale(scale));
        shader.setModel(model);
    }
*/
    public Mesh getMesh(){
        return mesh;
    }

    public void setMesh(Mesh mesh){
        this.mesh = mesh;
    }

    public StandardMaterial3D getMaterial(){
        return material;
    }


    public Vec3 getPosition(){
        return position;
    }
/*
    public void setPosition(Vec3 position){
        this.position = position;
        model.x4 = position.x;
        model.y4 = position.y;
        model.z4 = position.z;
        shader.setModel(model);
    }

    public Vec3 getScale() {
        return scale;
    }

    public void setScale(Vec3 scale) {
        this.scale = scale;
        model.x1 = scale.x;
        model.y2 = scale.y;
        model.z3 = scale.z;
        shader.setModel(model);
    }
*/
    public Vec4 getRotation(){
        return rotation;
    }

    public void setRotation(Vec4 rotation){
        this.rotation = rotation;
    }

    public Mat4 getModel(){
        return model;
    }
/*
    public void setModel(Mat4 model){
        this.model = model;
        shader.setModel(model);
    }
*/
    public Vec3 getRotationVelocity(){
        return rotationVelocity;
    }

    public void setRotationVelocity(Vec3 rotationVelocity){
        this.rotationVelocity = rotationVelocity;
    }

    public boolean isRotating() {
        return rotating;
    }

    public void setRotating(boolean rotating) {
        this.rotating = rotating;
    }
}
