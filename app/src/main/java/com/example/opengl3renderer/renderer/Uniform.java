package com.example.opengl3renderer.renderer;

import android.opengl.GLES32;

import com.example.opengl3renderer.math.Mat2;
import com.example.opengl3renderer.math.Mat3;
import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;

public class Uniform {
    String name;
    Object object;
    int id;

    public Uniform(String name, int programId){
        this.name = name;
        id = GLES32.glGetUniformLocation(programId, name);
    }
    public Uniform(String name, Object object, int programId){
        this.name = name;
        id = GLES32.glGetUniformLocation(programId, name);
        this.object = object;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public Object getObject(){
        return object;
    }

    public void setObject(Object object){
        this.object = object;
    }

    public void sendToShader(){
        //check type of object and send data to shader with gl call
        if(object instanceof Integer){
            GLES32.glUniform1i(id, (int) object);
        }
        if(object instanceof Float){
            GLES32.glUniform1f(id, (float) object);
        }
        if(object instanceof Vec2){
            GLES32.glUniform2f(id, ((Vec2) object).x, ((Vec2) object).y);
        }
        if(object instanceof Vec3){
            GLES32.glUniform3f(id, ((Vec3) object).x, ((Vec3) object).y, ((Vec3) object).z);
        }
        if(object instanceof Vec4){
            GLES32.glUniform4f(id, ((Vec4) object).x, ((Vec4) object).y, ((Vec4) object).z, ((Vec4) object).w);
        }
        if(object instanceof Mat2){
            GLES32.glUniformMatrix2fv(id, 1, false, ((Mat2) object).getValues());
        }
        if(object instanceof Mat3){
            GLES32.glUniformMatrix3fv(id, 1, false, ((Mat3) object).getValues());
        }
        if(object instanceof Mat4){
            GLES32.glUniformMatrix4fv(id, 1, false, ((Mat4) object).getValues());
        }
        if(object instanceof Vec3[]){
            // Converting Vec3 array into float array so that it can be passed to the shader
            float[] array = new float[((Vec3[]) object).length * 3];
            for(int i = 0; i < ((Vec3[]) object).length; i++){
                array[i * 3] = ((Vec3[]) object)[i].x;
                array[i * 3 + 1] = ((Vec3[]) object)[i].y;
                array[i * 3 + 2] = ((Vec3[]) object)[i].z;
            }
            GLES32.glUniform3fv(id, ((Vec3[]) object).length, array, 0);
        }
        if(object instanceof Texture){
            GLES32.glUniform1i(id, ((Texture) object).getId() - 1);
        }
    }
}
