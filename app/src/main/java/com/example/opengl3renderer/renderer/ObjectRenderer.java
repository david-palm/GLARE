package com.example.opengl3renderer.renderer;

import android.content.Context;
import android.opengl.GLES32;
import android.util.Log;

import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.object3d.Camera;
import com.example.opengl3renderer.object3d.Object3D;
import com.example.opengl3renderer.object3d.PbrObjectShader;

public class ObjectRenderer {
    Context context;

    int width;
    int height;

    PbrObjectShader shader;
    Camera camera;
    Object3D cube;

    Vec3 lightPosition;

    float fov = 45.0f;
    long time;
    boolean cubeRotating = true;



    public ObjectRenderer(Context context, int width, int height){
        this.context = context;
        this.width = width;
        this.height = height;

        shader = new PbrObjectShader(context);

        shader.bind();

        camera = new Camera(new Vec3(0.0f, 0.0f, -5.0f), fov, 9.0f/16.0f);

        //loading textures
        Texture albedo = new Texture(context, "albedo");
        Texture normal = new Texture(context, "normal");
        Texture metallic = new Texture(context, "metallic");
        Texture roughness = new Texture(context, "roughness");
        Texture ao = new Texture(context, "ao");



        //cube = new Object3D(new Cube(), shader);

        lightPosition = new Vec3(0.0f, 0.0f, -2.0f);
        Vec3 lightColor = new Vec3(1.0f, 1.0f, 1.0f);

        //shader.setLightPosition(lightPosition);
        //program.setLightColor(lightColor);

        //program.setView(Mat4.translation(new Vec3(0.0f, 0.0f, -5.0f)));
        //program.setProjection(camera.getProjection());

        //Set Viewport
        GLES32.glViewport(0,0, width, height);
        //Enable Depth Test
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);

        Log.d("ObjectRenderer", "Width= " + width + " Height= " + height);
        time = 0;
    }

    public void drawFrame(){
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        //program.use();

        //program.setLightPosition(lightPosition);
        //a new model matrix is created and put into the uniforms list
        //cube.translate(new Vec3(0.0f, 0.0f, 0.01f));
        if(cubeRotating) {
            //cube.setRotation(new Vec4(0.0f, 1.0f, 0.0f, time / 100.0f * (float) Math.toRadians(30.0f)));
            //cube.rotate(new Vec4(0.0f, 1.0f, 0.0f, time / 100.0f * (float) Math.toRadians(30.0f)));
        }

        //cube.render();
        time++;
    }

}
