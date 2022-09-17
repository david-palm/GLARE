package com.example.opengl3renderer.object3d;

import android.content.Context;
import android.util.Log;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Shader;
import com.example.opengl3renderer.renderer.Uniform;

public class ObjectShader extends Shader {
    protected Uniform model;
    protected Uniform view;
    protected Uniform projection;

    public ObjectShader(Context context, String vertexShaderPath, String fragmentShaderPath) {
        super(context, vertexShaderPath, fragmentShaderPath);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
        view = new Uniform("uView", new Mat4(), shaderProgramId);
        projection = new Uniform("uProjection", new Mat4(), shaderProgramId);
    }

    public ObjectShader(String vertexShaderSource, String fragmentShaderSource){
        super(vertexShaderSource, fragmentShaderSource);
        model = new Uniform("uModel", new Mat4(), shaderProgramId);
        view = new Uniform("uView", new Mat4(), shaderProgramId);
        projection = new Uniform("uProjection", new Mat4(), shaderProgramId);
    }

    protected void setModel(Mat4 model){
        this.model.setObject(model);
        this.model.sendToShader();
    }
    protected void setView(Mat4 view){
        this.view.setObject(view);
        this.view.sendToShader();
    }
    protected void setProjection(Mat4 projection) {
        this.projection.setObject(projection);
        this.projection.sendToShader();
    }
}
