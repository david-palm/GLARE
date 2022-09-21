package com.example.opengl3renderer.renderer;

import android.opengl.GLES32;

import java.util.List;

public abstract class Mesh {
    float[] vertices;
    int[] indices;
    VertexArray vertexArray;
    protected List<BufferElement> elements;

    public Mesh(float[] vertices, int[] indices, List<BufferElement> elements){
        this.vertices = vertices;
        this.indices = indices;
        vertexArray = new VertexArray();
        this.elements = elements;
        vertexArray.addVertexBuffer(new VertexBuffer(vertices, new BufferLayout(elements)));
        vertexArray.setIndexBuffer(new IndexBuffer(indices));
    }

    public float[] getVertices(){
        return vertices;
    }

    public int[] getIndices(){
        return indices;
    }

    public VertexArray getVertexArray(){
        return vertexArray;
    }

    public void onRender(){
        vertexArray.bind();
        GLES32.glDrawElements(GLES32.GL_TRIANGLES, indices.length, GLES32.GL_UNSIGNED_INT, 0);
        vertexArray.unbind();
    }

}
