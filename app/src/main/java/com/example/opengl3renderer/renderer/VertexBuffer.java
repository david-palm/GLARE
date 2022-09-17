package com.example.opengl3renderer.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import android.opengl.GLES32;

public class VertexBuffer implements Buffer{
    int[] vboId = new int[1];
    BufferLayout layout;
    public VertexBuffer(FloatBuffer vertices, int size){
        layout = new BufferLayout(new ArrayList<BufferElement>());
        ///////////////////////
        //Fehler liegt bei BufferLayout
        //Vertices müssen wahrscheinlich als Vec3 Element in das Buffer Layout reingetan werden
        //Denn BufferElement.getComponentCount() muss richtige Zahl zurückgeben
        ///////////////////////
        GLES32.glGenBuffers(1, vboId, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboId[0]);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, size, vertices, GLES32.GL_STATIC_DRAW);
    }

    public VertexBuffer(float[] vertices, BufferLayout bufferLayout){
        layout = bufferLayout;
        //creating a vertex buffer that stores vertices
        FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(vertices.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(vertices).position(0);

        GLES32.glGenBuffers(1, vboId, 0);
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboId[0]);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER, vertices.length * 4, vertexBuffer, GLES32.GL_STATIC_DRAW);
    }

    public void bind(){
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboId[0]);
    }

    public void unbind(){
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GLES32.glDeleteBuffers(1, vboId, 0);
    }

    public BufferLayout getLayout(){
        return layout;
    }

    public void setBufferLayout(BufferLayout layout){
        this.layout = layout;
    }
}