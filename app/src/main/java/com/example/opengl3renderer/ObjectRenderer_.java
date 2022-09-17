package com.example.opengl3renderer;

import android.content.Context;
import android.opengl.GLES32;
import android.util.Log;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.utils.ShaderProgram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class ObjectRenderer_ {
    /*
    Context context;

    int width;
    int height;

    ShaderProgram shaderProgram;
    PBRShaderProgram pbrShaderProgram;

    Cube cube;

    IntBuffer indexBuffer;
    int[] vaoIDs = new int[1];
    int[] vboIDs = new int[2];

    List<Uniform> uniforms;

    Mat4 model;
    Mat4 view;
    Mat4 projection;

    float fov = 45.0f;
    long time;
    boolean cubeRotating = true;



    public ObjectRenderer_(ShaderProgram shaderProgram, Context context, int width, int height){
        this.shaderProgram = shaderProgram;
        pbrShaderProgram = new PBRShaderProgram(context);
        this.context = context;
        this.width = width;
        this.height = height;

        cube = new Cube();
        loadVerticesToVAO(cube.getVertices());
        uniforms = new ArrayList<Uniform>();

        //Add Texture
        Texture albedo = new Texture(context, "albedo");
        Texture normal = new Texture(context, "normal");
        Texture metallic = new Texture(context, "metallic");
        Texture roughness = new Texture(context, "roughness");
        Texture ao = new Texture(context, "ao");

        Material material = new Material(albedo, normal, metallic, roughness, ao);

        Vec3 lightPosition = new Vec3(0.0f, 0.0f, 2.0f);
        Vec3 lightColor = new Vec3(1.0f, 1.0f, 1.0f);
        Vec3 cameraPosition = new Vec3(0.0f, 0.0f, 5.0f);

        model = new Mat4(1);
        Camera camera = new Camera(cameraPosition, fov);
        view = Mat4.translation(new Vec3(0.0f, 0.0f, -5.0f));
        projection = camera.getProjection();


        uniforms.add(new Uniform("uModel", model, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uView", view, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uProjection", projection, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uCameraPosition", cameraPosition, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uAlbedo", albedo, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uNormal", normal, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uMetallic", metallic, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uRoughness", roughness, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uAO", ao, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uLightPosition", lightPosition, shaderProgram.getProgramId()));
        uniforms.add(new Uniform("uLightColor", lightColor, shaderProgram.getProgramId()));


        create();
        Log.d("ObjectRenderer", "Width= " + width + " Height= " + height);
        time = 0;
    }

    public void create(){
        //Set Viewport
        GLES32.glViewport(0,0, width, height);
        //Enable Depth Test
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);
        //Add Uniforms

        model = new Mat4(1);
        view = Mat4.translation(new Vec3(0, 0, -3));
        projection = Mat4.perspective((float)Math.toRadians(fov), 9.0f/16.0f, 0.1f, 100);
    }

    public void drawFrame(){
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
        GLES32.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        shaderProgram.use();
        //pbrShaderProgram.use();

        //a new model matrix is created and put into the uniforms list
        if(cubeRotating) {
            //model = Mat4.rotation(new Vec3(0.5f, 1.0f, 0).normalize(), time / 100.0f * (float) (Math.toRadians(30f)));
            //view.z4 = -3 - time * 0.001f;
            model = Mat4.rotation(new Vec3(0.0f, 1.0f, 0.0f), time / 100.0f * (float) Math.toRadians(30.0f));
            uniforms.set(0, new Uniform("uModel", model, shaderProgram.getProgramId()));
            //pbrShaderProgram.updateModel(model);


            //Vec4 lightPosition = new Vec4(1.0f, 2.0f, 3.0f, 1.0f);

            //uniforms.set(1, new Uniform("uView", view, shaderProgram.getProgramID()));
            //uniforms.set(3, new Uniform("uCameraPosition", new Vec3(0, 0, 3 + time * 0.001f), shaderProgram.getProgramID()));
        }

        //Set Uniforms and textures
        pbrShaderProgram.updateAllUniforms();
        for(Uniform uniform : uniforms){
            if(uniform.getObject() instanceof Vec3){
                shaderProgram.setUniformVec3(uniform.getId(), (Vec3) uniform.getObject());
            }
            if(uniform.getObject() instanceof Vec4){
                shaderProgram.setUniformVec4(uniform.getId(), (Vec4) uniform.getObject());
            }
            if(uniform.getObject() instanceof Mat4){
                shaderProgram.setUniformMat4(uniform.getId(), (Mat4) uniform.getObject());
            }
            //if uniform is a texture it is immediately bound
            if(uniform.getObject() instanceof Texture){
                Texture texture = (Texture) uniform.getObject();
                GLES32.glActiveTexture(33983 + texture.getId());
                shaderProgram.setUniformInt(uniform.getId(), texture.getId()-1);
                GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, texture.getId());
            }
        }

        GLES32.glBindVertexArray(vaoIDs[0]);
        GLES32.glDrawElements(GLES32.GL_TRIANGLES, cube.getIndices().length, GLES32.GL_UNSIGNED_INT, 0);
        GLES32.glBindVertexArray(0);
        time++;
    }

    public void setModelMatrix(Mat4 modelMatrix){
        this.model = modelMatrix;
    }

    public void setRotating(boolean b){
        cubeRotating = b;
    }

    public boolean getRotating(){
        return cubeRotating;
    }

    public void rotateObject(Vec3 axis, float alpha){
        model = model.multiply(Mat4.rotation(axis, alpha));
    }

    public void changeCameraPosition(Vec3 position){
        view.x4 = position.x;
        view.y4 = position.y;
        view.z4 = -position.z;
        //uniforms.set(1, new Uniform("uView", view, shaderProgram.getProgramID()));
        //uniforms.set(3, new Uniform("uCameraPosition", position, shaderProgram.getProgramID()));
    }

    void loadVerticesToVAO(float[] positions){
        FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(cube.getVertices().length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        vertexBuffer.put(cube.getVertices()).position(0);

        indexBuffer = ByteBuffer.allocateDirect(cube.getIndices().length*4).order(ByteOrder.nativeOrder()).asIntBuffer();
        indexBuffer.put(cube.getIndices()).position(0);

        //storeDataInAttributeLis()
        GLES32.glGenBuffers(2, vboIDs, 0);

        //Pass position data to shaders
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboIDs[0]);
        vertexBuffer.position(0);
        GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER,  positions.length*4, vertexBuffer, GLES32.GL_STATIC_DRAW);
        //Pass indices to shaders
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER,vboIDs[1]);
        indexBuffer.position(0);
        GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, cube.getIndices().length*4, indexBuffer, GLES32.GL_STATIC_DRAW);


        GLES32.glGenVertexArrays(1, vaoIDs, 0);
        GLES32.glBindVertexArray(vaoIDs[0]);
        //
        GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboIDs[0]);
        GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER, vboIDs[1]);

        GLES32.glEnableVertexAttribArray(0);
        GLES32.glEnableVertexAttribArray(1);
        GLES32.glEnableVertexAttribArray(2);
        GLES32.glEnableVertexAttribArray(3);
        GLES32.glVertexAttribPointer(0,3, GLES32.GL_FLOAT, false , 4*11, 0);
        GLES32.glVertexAttribPointer(1,2, GLES32.GL_FLOAT, false , 4*11, 4*3);
        GLES32.glVertexAttribPointer(2,3, GLES32.GL_FLOAT, false , 4*11, 4*5);
        GLES32.glVertexAttribPointer(3,3, GLES32.GL_FLOAT, false , 4*11, 4*8);
        //Unbind
        GLES32.glBindVertexArray(0);
    }

     */

}
