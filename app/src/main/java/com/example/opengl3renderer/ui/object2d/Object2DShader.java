package com.example.opengl3renderer.ui.object2d;

import android.content.Context;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.renderer.Shader;
import com.example.opengl3renderer.renderer.Uniform;

public class Object2DShader extends Shader {
    protected Uniform model;
    public Object2DShader(String vertexShaderSource, String fragmentShaderSource) {
        super(vertexShaderSource, fragmentShaderSource);
        model = new Uniform("uModel", shaderProgramId);
    }

    public Object2DShader(Context context, String vertexShaderPath, String fragmentShaderPath) {
        super(context, vertexShaderPath, fragmentShaderPath);
        model = new Uniform("uModel", shaderProgramId);
    }

    public void setModel(Mat4 model){
        this.model.setObject(model);
        this.model.sendToShader();
    }
}
