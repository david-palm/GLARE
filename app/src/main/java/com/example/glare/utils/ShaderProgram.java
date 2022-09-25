package com.example.glare.utils;

import android.content.Context;
import android.opengl.GLES32;

import com.example.glare.renderer.Uniform;

import java.util.List;

public class ShaderProgram {
    protected int programId;
    protected int vertexShaderId;
    protected int fragmentShaderId;
    protected List<Uniform> uniforms;

    public ShaderProgram(Context context, String vertexShaderPath, String fragmentShaderPath){
        //loading shaders from assets
        String vertexShaderSource = ShaderUtils.loadShaderFromAssets(context, vertexShaderPath);
        String fragmentShaderSource = ShaderUtils.loadShaderFromAssets(context, fragmentShaderPath);
        //creating shaders
        vertexShaderId = ShaderUtils.loadShader(GLES32.GL_VERTEX_SHADER, vertexShaderSource);
        fragmentShaderId = ShaderUtils.loadShader(GLES32.GL_FRAGMENT_SHADER, fragmentShaderSource);
        //create shader program
        programId = ShaderUtils.createShaderProgram(vertexShaderId, fragmentShaderId);
    }

    public int getProgramId(){
        return programId;
    }

    public void use(){
        GLES32.glUseProgram(programId);
    }

    public void clean(){
        GLES32.glUseProgram(0);
        GLES32.glDetachShader(programId, vertexShaderId);
        GLES32.glDetachShader(programId, fragmentShaderId);
        GLES32.glDeleteShader(vertexShaderId);
        GLES32.glDeleteShader(fragmentShaderId);
        GLES32.glDeleteProgram(programId);
    }

    public void bindAttribute(int id, String name){
        GLES32.glBindAttribLocation(programId, id, name);
    }

    public void updateAllUniforms(){
        for(Uniform uniform : uniforms){
            uniform.sendToShader();
        }
    }
}
