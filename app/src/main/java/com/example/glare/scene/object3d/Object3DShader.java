package com.example.glare.scene.object3d;

import android.content.Context;

import com.example.glare.math.Mat4;
import com.example.glare.renderer.Shader;
import com.example.glare.renderer.Uniform;

public class Object3DShader extends Shader {
    protected Uniform model;
    protected Uniform view;
    protected Uniform projection;

    public Object3DShader(Context context, String vertexShaderPath, String fragmentShaderPath){
        super(context, vertexShaderPath, fragmentShaderPath);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
        view = new Uniform("uView", new Mat4(), shaderProgramId);
        projection = new Uniform("uProjection", new Mat4(), shaderProgramId);
    }

    public Object3DShader(String vertexShaderSource, String fragmentShaderSource){
        super(vertexShaderSource, fragmentShaderSource);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
        view = new Uniform("uView", new Mat4(), shaderProgramId);
        projection = new Uniform("uProjection", new Mat4(), shaderProgramId);
    }

    public void setModel(Mat4 model){
        this.model.setObject(model);
        this.model.sendToShader();
    }
    public void setView(Mat4 view){
        this.view.setObject(view);
        this.view.sendToShader();
    }
    public void setProjection(Mat4 projection) {
        this.projection.setObject(projection);
        this.projection.sendToShader();
    }
}
