package com.example.opengl3renderer.ui;

import android.content.Context;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.renderer.Shader;
import com.example.opengl3renderer.renderer.Uniform;

public class Shader2D extends Shader {
    protected Uniform model;

    public Shader2D(String vertexShaderSource, String fragmentShaderSource) {
        super(vertexShaderSource, fragmentShaderSource);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
    }

    public Shader2D(Context context, String vertexShaderPath, String fragmentShaderPath) {
        super(context, vertexShaderPath, fragmentShaderPath);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
    }

    protected void setModel(Mat4 model){
        this.model.setObject(model);
        this.model.sendToShader();
    }
}
