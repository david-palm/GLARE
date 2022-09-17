package com.example.opengl3renderer.renderer;

import android.util.Log;
import android.opengl.GLES32;


public class BufferElement {
    ShaderDataType shaderDataType;
    String name;
    int size;
    int offset;
    boolean normalized;
    public BufferElement(ShaderDataType shaderDataType, String name, boolean normalized){
        this.shaderDataType = shaderDataType;
        this.name = name;
        size = sizeOfDataType(shaderDataType);
        offset = 0;
        this.normalized = normalized;
    }
    public static int sizeOfDataType(ShaderDataType shaderDataType) {
        switch (shaderDataType) {
            case Bool:
                return 1;
            case Int:
            case Float:
                return 4;
            case Vec2:
                return 8;
            case Vec3:
                return 12;
            case Vec4:
            case Mat2:
                return 16;
            case Mat3:
                return 36;
            case Mat4:
                return 64;
        }

        Log.e("Buffer Element", "Unknown shader data type!");
        return -1;
    }

    public int getComponentCount(){
        switch(shaderDataType){
            case Bool:
            case Int:
            case Float:
                return 1;
            case Vec2:
                return 2;
            case Vec3:
                return 3;
            case Vec4:
            case Mat2:
                return 4;
            case Mat3:
                return 9;
            case Mat4:
                return 16;
        }
        Log.e("Buffer Element", "Unknown shader data type!");
        return -1;
    }

    public int getShaderBaseType() {
        switch(shaderDataType){
            case Bool:
                return GLES32.GL_BOOL;
            case Int:
                return GLES32.GL_INT;
            case Float:
            case Vec2:
            case Vec3:
            case Vec4:
            case Mat2:
            case Mat3:
            case Mat4:
                return GLES32.GL_FLOAT;
        }
        Log.e("Buffer Element", "Unknown shader data type!");
        return -1;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return offset;
    }

    public boolean isNormalized() {
        return normalized;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
