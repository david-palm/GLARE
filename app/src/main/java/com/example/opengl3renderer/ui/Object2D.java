package com.example.opengl3renderer.ui;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Mesh;
import com.example.opengl3renderer.renderer.Shader;

public class Object2D {
    Mesh mesh;
    Shader shader;

    Vec3 position;
    Vec3 scale;
    Vec4 rotation;
    Mat4 model;

    public Object2D(){

    }
}
