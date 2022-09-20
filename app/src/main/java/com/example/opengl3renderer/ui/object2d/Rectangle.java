package com.example.opengl3renderer.ui.object2d;

import com.example.opengl3renderer.renderer.BufferElement;
import com.example.opengl3renderer.renderer.Mesh;
import com.example.opengl3renderer.renderer.ShaderDataType;

import java.util.Arrays;

public class Rectangle extends Mesh {
    public Rectangle() {
        super(new float[]{ -1.0f, -1.0f, 0.0f, 0.0f,
                           -1.0f, 1.0f, 0.0f, 1.0f,
                            1.0f, 1.0f, 1.0f, 1.0f,
                            1.0f, -1.0f, 1.0f, 0.0f }, new int[]{ 0, 1, 2, 0, 2, 3},
                Arrays.asList(new BufferElement[] { new BufferElement(ShaderDataType.Vec2, "aPosition", false),
                                                 new BufferElement(ShaderDataType.Vec2, "aTexCoord", false)}));
    }
}
