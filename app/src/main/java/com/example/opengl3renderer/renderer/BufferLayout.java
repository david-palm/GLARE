package com.example.opengl3renderer.renderer;

import java.util.ArrayList;

public class BufferLayout {
    ArrayList<BufferElement> elements;
    int stride;
    public BufferLayout(ArrayList<BufferElement> elements){
        this.elements = elements;
        stride = 0;
        calculateOffsetAndStride();
    }

    private void calculateOffsetAndStride(){
        stride = 0;
        int offset = 0;
        for (BufferElement element: elements){
            element.setOffset(offset);
            offset += element.getSize();
            stride += element.getSize();
        }
    }

    public ArrayList<BufferElement> getElements(){
        return elements;
    }

    public int getStride(){
        return stride;
    }
}
