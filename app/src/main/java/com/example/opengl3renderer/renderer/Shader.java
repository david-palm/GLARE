package com.example.opengl3renderer.renderer;

import android.content.Context;
import android.opengl.GLES32;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Shader {
    protected int shaderProgramId;
    public Shader(String vertexShaderSource, String fragmentShaderSource){
        createShader(vertexShaderSource, fragmentShaderSource);
    }

    public Shader(Context context, String vertexShaderPath, String fragmentShaderPath){
        if(vertexShaderPath == null){
            Log.e("Shader creation error", "Vertex shader path is invalid!");
            return;
        }
        if(fragmentShaderPath == null){
            Log.e("Shader creation error", "Fragment shader path is invalid!");
            return;
        }
        String vertexShaderSource = loadShaderFromAssets(context, vertexShaderPath);
        String fragmentShaderSource = loadShaderFromAssets(context, fragmentShaderPath);
        createShader(vertexShaderSource, fragmentShaderSource);
    }

    public void createShader(String vertexShaderSource, String fragmentShaderSource){
        // Creating and compiling vertex shader
        int vertexShaderId = GLES32.glCreateShader(GLES32.GL_VERTEX_SHADER);
        GLES32.glShaderSource(vertexShaderId, vertexShaderSource);
        GLES32.glCompileShader(vertexShaderId);
        //Checking for compile errors
        int[] compiled = new int[1];
        GLES32.glGetShaderiv(vertexShaderId, GLES32.GL_COMPILE_STATUS, compiled, 0);
        if(compiled[0] == 0){
            Log.e("GLES32 Error", "Vertex shader with id: " + vertexShaderId + " did not compile:");
            Log.e("GLES32 Error", GLES32.glGetShaderInfoLog(vertexShaderId));
            GLES32.glDeleteShader(vertexShaderId);
            return;
        }

        // Creating and compiling fragment shader
        int fragmentShaderId = GLES32.glCreateShader(GLES32.GL_FRAGMENT_SHADER);
        GLES32.glShaderSource(fragmentShaderId, fragmentShaderSource);
        GLES32.glCompileShader(fragmentShaderId);
        // Checking for compile errors
        GLES32.glGetShaderiv(fragmentShaderId, GLES32.GL_COMPILE_STATUS, compiled, 0);
        if(compiled[0] == 0){
            Log.e("GLES32 Error", "Fragment shader with id: " + fragmentShaderId + " did not compile:");
            Log.e("GLES32 Error", GLES32.glGetShaderInfoLog(fragmentShaderId));
            GLES32.glDeleteShader(fragmentShaderId);
            return;
        }

        // Creating shader program
        shaderProgramId = GLES32.glCreateProgram();
        GLES32.glAttachShader(shaderProgramId, vertexShaderId);
        GLES32.glAttachShader(shaderProgramId, fragmentShaderId);
        GLES32.glLinkProgram(shaderProgramId);
        // Checking for linking errors
        int[] linked = new int[1];
        GLES32.glGetProgramiv(shaderProgramId, GLES32.GL_LINK_STATUS, linked, 0);
        if(linked[0] == 0){
            Log.e("GLES32 Error", "Shader program with id " + shaderProgramId + " did not link:");
            Log.e("GLES32 Error", GLES32.glGetProgramInfoLog(shaderProgramId));
            GLES32.glDeleteProgram(shaderProgramId);
            return;
        }

        GLES32.glDetachShader(shaderProgramId, vertexShaderId);
        GLES32.glDetachShader(shaderProgramId, fragmentShaderId);
    }

    private String loadShaderFromAssets(Context context, String filePath){
        InputStream inputStream = null;
        byte[] buffer;
        String shaderSource = null;
        try {
            inputStream = context.getAssets().open(filePath);
            //Create buffer with same size as input stream
            buffer = new byte[inputStream.available()];
            //Read text file into buffer
            inputStream.read(buffer);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //Write buffer to output stream
            byteArrayOutputStream.write(buffer);
            //Closing input and output stream
            byteArrayOutputStream.close();
            inputStream.close();

            shaderSource = byteArrayOutputStream.toString();

        }
        catch(IOException ioException){
            inputStream = null;
        }
        return shaderSource;
    }

    public void bind(){
        GLES32.glUseProgram(shaderProgramId);
    }

    public void unbind(){
        GLES32.glUseProgram(0);
    }

    public void delete(){
        GLES32.glDeleteProgram(shaderProgramId);
    }

}
