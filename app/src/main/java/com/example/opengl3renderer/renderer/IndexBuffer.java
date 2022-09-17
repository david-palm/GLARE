package com.example.opengl3renderer.renderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import android.opengl.GLES32;

public class IndexBuffer implements Buffer{
    int[] eboId = new int[1];
    public IndexBuffer(IntBuffer indices, int count){
        GLES32.glGenBuffers(1, eboId, 0);
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, eboId[0]);
        GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, count, indices, GLES32.GL_STATIC_DRAW);
    }

    public IndexBuffer(int[] indices){
        //creating index buffer that stores indices
        IntBuffer indexBuffer = ByteBuffer.allocateDirect(indices.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        indexBuffer.put(indices).position(0);

        GLES32.glGenBuffers(1, eboId, 0);
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, eboId[0]);
        GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, indices.length * 4, indexBuffer, GLES32.GL_STATIC_DRAW);
    }

    public void bind(){
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, eboId[0]);
    }

    public void unbind(){
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void delete() {
        GLES32.glDeleteBuffers(1, eboId, 0);
    }
}
