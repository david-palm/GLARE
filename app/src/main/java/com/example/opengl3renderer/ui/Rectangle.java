package com.example.opengl3renderer.ui;

import com.example.opengl3renderer.renderer.Mesh;

public class Rectangle extends Mesh {
    public Rectangle() {
        super(new float[]{ -1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f, 1.0f, -1.0f }, new int[]{ 0, 1, 2, 0, 2, 3});
    }
}
