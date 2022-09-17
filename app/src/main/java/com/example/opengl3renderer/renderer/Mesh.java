package com.example.opengl3renderer.renderer;

import android.opengl.GLES32;

import com.example.opengl3renderer.renderer.BufferElement;
import com.example.opengl3renderer.renderer.BufferLayout;
import com.example.opengl3renderer.renderer.IndexBuffer;
import com.example.opengl3renderer.renderer.ShaderDataType;
import com.example.opengl3renderer.renderer.VertexArray;
import com.example.opengl3renderer.renderer.VertexBuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public abstract class Mesh {
    float[] vertices;
    int[] indices;
    VertexArray vertexArray;

    public Mesh(float[] vertices, int[] indices){
        this.vertices = vertices;
        this.indices = indices;

        vertexArray = new VertexArray();
        ArrayList<BufferElement> elements = new ArrayList<BufferElement>();

        elements.add(new BufferElement(ShaderDataType.Vec3, "aPosition", false));
        elements.add(new BufferElement(ShaderDataType.Vec2, "aTexCoord", false));
        elements.add(new BufferElement(ShaderDataType.Vec3, "aNormal", false));
        elements.add(new BufferElement(ShaderDataType.Vec3, "aTangent", false));
        BufferLayout bufferLayout = new BufferLayout(elements);
        vertexArray.addVertexBuffer(new VertexBuffer(vertices, bufferLayout));
        vertexArray.setIndexBuffer(new IndexBuffer(indices));
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public VertexArray getVertexArray(){
        return vertexArray;
    }

    public void render(){
        vertexArray.bind();
        GLES32.glDrawElements(GLES32.GL_TRIANGLES, indices.length, GLES32.GL_UNSIGNED_INT, 0);
        vertexArray.unbind();
    }

}
