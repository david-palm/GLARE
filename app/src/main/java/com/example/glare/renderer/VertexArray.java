package com.example.glare.renderer;

import android.opengl.GLES32;

import java.util.ArrayList;

public class VertexArray {
    int[] vaoId = new int[1];
    ArrayList<VertexBuffer> vertexBuffers;
    IndexBuffer indexBuffer;
    public VertexArray(){
        vertexBuffers = new ArrayList<VertexBuffer>();
        GLES32.glGenVertexArrays(1, vaoId, 0);
    }

    public void bind(){
        GLES32.glBindVertexArray(vaoId[0]);
    }

    public void unbind(){
        GLES32.glBindVertexArray(0);
    }

    public void addVertexBuffer(VertexBuffer vertexBuffer){
        GLES32.glBindVertexArray(vaoId[0]);
        vertexBuffer.bind();
        int index = 0;
        BufferLayout bufferLayout = vertexBuffer.getLayout();
        for(BufferElement bufferElement: bufferLayout.getElements())
        {
            GLES32.glVertexAttribPointer(index,
                    bufferElement.getComponentCount(),
                    bufferElement.getShaderBaseType(),
                    bufferElement.isNormalized(),
                    bufferLayout.getStride(),
                    bufferElement.getOffset());
            GLES32.glEnableVertexAttribArray(index);
            index++;
        }
        vertexBuffers.add(vertexBuffer);
    }

    public void setIndexBuffer(IndexBuffer indexBuffer){
        GLES32.glBindVertexArray(vaoId[0]);
        indexBuffer.bind();
        this.indexBuffer = indexBuffer;
    }
}
