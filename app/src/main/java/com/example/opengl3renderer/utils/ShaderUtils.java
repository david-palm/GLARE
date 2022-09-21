package com.example.opengl3renderer.utils;

import android.content.Context;
import android.opengl.GLES32;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShaderUtils {
    public static int loadShader(int shaderType, String shaderSource){
        int shaderID = GLES32.glCreateShader(shaderType);
        if(shaderID == 0){
            Log.e("ShaderUtils", "Error occurred while creating shader");
            // ShaderID is 0 if an error occurred while creating the shader object (probably doesn't compile)
            return 0;
        }
        // Replacing shader code
        GLES32.glShaderSource(shaderID, shaderSource);
        //
        GLES32.glCompileShader(shaderID);
        // Check compile status
        System.out.println("Shader compiled");
        int[] compiled = new int[1];
        GLES32.glGetShaderiv(shaderID, GLES32.GL_COMPILE_STATUS, compiled, 0);
        if(compiled[0] == 0){
            // Shader didn't compile
            Log.e("ShaderUtils", "Shader with shader Id: "+ shaderID + " did not compile");
            GLES32.glDeleteShader(shaderID);
            return 0;
        }
        return shaderID;

    }
    //TODO: Needs ArrayList of Uniforms as parameter, so that it works for every type of renderer
    public static int createShaderProgram(int vertexShaderID, int fragmentShaderID){

        int programObjectID = GLES32.glCreateProgram();
        if(programObjectID==0){
            Log.e("ShaderUtils", "Error occurred while creating shader program");
            // ProgramObjectID is 0 when an error occurred while creating
            return 0;
        }
        GLES32.glAttachShader(programObjectID, vertexShaderID);
        GLES32.glAttachShader(programObjectID, fragmentShaderID);

        GLES32.glBindAttribLocation(programObjectID, 0, "aPosition");
        GLES32.glBindAttribLocation(programObjectID, 1, "aTexCoords");
        GLES32.glBindAttribLocation(programObjectID, 2, "aNormal");
        GLES32.glLinkProgram(programObjectID);
        // Check link status
        int[] linked = new int[1];
        GLES32.glGetProgramiv(programObjectID, GLES32.GL_LINK_STATUS, linked,0);
        // Check if linking had errors
        if(linked[0]==0){
            Log.e("ShaderUtils", "Error occurred while linking");
            GLES32.glDeleteProgram(programObjectID);
            return 0;
        }
        return programObjectID;
    }

    public static String loadShaderFromAssets(Context context, String filePath){
        String shader = null;

        if(filePath == null){
            return shader;
        }
        InputStream inputStream = null;
        byte[] buffer;

        try {
            inputStream = context.getAssets().open(filePath);
            // Create buffer with same size as input stream
            buffer = new byte[inputStream.available()];
            // Read text file into buffer
            inputStream.read(buffer);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // Write buffer to output stream
            byteArrayOutputStream.write(buffer);
            // Closing input and output stream
            byteArrayOutputStream.close();
            inputStream.close();

            shader = byteArrayOutputStream.toString();

        }
        catch(IOException ioException){
            inputStream = null;
        }
        return shader;
    }
}
