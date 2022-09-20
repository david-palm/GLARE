package com.example.opengl3renderer.scene.object3d;

import com.example.opengl3renderer.renderer.BufferElement;
import com.example.opengl3renderer.renderer.Mesh;
import com.example.opengl3renderer.renderer.ShaderDataType;

import java.util.Arrays;

public class Cube extends Mesh {
    public Cube(){
                                //positions           uv            normals              tangents
        super(new float[]{  -0.5f, -0.5f, -0.5f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f, -0.5f,  1.0f,  1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f, -0.5f,  1.0f,  1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f, -0.5f, -0.5f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,  0.0f,

                            -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f,  0.5f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f,  0.5f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,  0.0f,

                            -0.5f,  0.5f,  0.5f,  1.0f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,
                            -0.5f,  0.5f, -0.5f,  1.0f,  1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,
                            -0.5f, -0.5f, -0.5f,  0.0f,  1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,
                            -0.5f, -0.5f, -0.5f,  0.0f,  1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,
                            -0.5f, -0.5f,  0.5f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,
                            -0.5f,  0.5f,  0.5f,  1.0f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f, -1.0f,

                             0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,
                             0.5f,  0.5f, -0.5f,  1.0f,  1.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,
                             0.5f, -0.5f, -0.5f,  0.0f,  1.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,
                             0.5f, -0.5f, -0.5f,  0.0f,  1.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,
                             0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,
                             0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  1.0f,  0.0f, 0.0f,  0.0f,  0.0f, -1.0f,

                            -0.5f, -0.5f, -0.5f,  0.0f,  1.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f, -0.5f, -0.5f,  1.0f,  1.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f, -0.5f, -0.5f,  0.0f,  1.0f,  0.0f, -1.0f, 0.0f,  1.0f,  0.0f,  0.0f,

                            -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f, -0.5f,  1.0f,  1.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                             0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f,
                            -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,  1.0f, 0.0f,  1.0f,  0.0f,  0.0f},
        new int[]{  0, 1, 2,
                    3, 4, 5,
                    6, 7, 8,
                    9, 10, 11,
                    12, 13, 14,
                    15, 16, 17,
                    18, 19, 20,
                    21, 22, 23,
                    24, 25, 26,
                    27, 28, 29,
                    30, 31, 32,
                    33, 34, 35}, Arrays.asList(new BufferElement[]{ new BufferElement(ShaderDataType.Vec3, "aPosition", false),
                                                 new BufferElement(ShaderDataType.Vec2, "aTexCoord", false),
                                                 new BufferElement(ShaderDataType.Vec3, "aNormal", false),
                                                 new BufferElement(ShaderDataType.Vec3, "aTangent", false)}));
    }

}