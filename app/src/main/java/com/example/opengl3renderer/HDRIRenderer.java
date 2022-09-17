package com.example.opengl3renderer;

import android.content.Context;
import android.opengl.GLES32;

import com.example.opengl3renderer.math.Mat4;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.utils.ImageUtils;
import com.example.opengl3renderer.utils.ShaderProgram;
import com.example.opengl3renderer.JavaHDR.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;




public class HDRIRenderer {
    ShaderProgram shaderProgram;
    Context context;
    int width;
    int height;

    IntBuffer indexBuffer;
    int[] vaoIDs = new int[1];
    int[] vboIDs = new int[2];

    Mat4 projection;
    Mat4[] cubeCapture;

    List<Integer> uniformLocations;
    List<Integer> textureIDs;

    int[] captureFBOs;
    int[] captureRBOs;

    HDRImage hdrimage;

    public HDRIRenderer(ShaderProgram shaderProgram, Context context, int width, int height, String name) throws IOException {
        this.shaderProgram = shaderProgram;
        this.context = context;
        this.width = width;
        this.height = height;
        this.hdrimage = HDREncoder.readHDR(new File("textures/background.hdri"), true);
        init();
    }

    void init(){
        GLES32.glGenFramebuffers(1, captureFBOs, 0);
        GLES32.glGenRenderbuffers(1, captureRBOs, 0);

        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, captureFBOs[0]);
        GLES32.glBindRenderbuffer(GLES32.GL_RENDERBUFFER, captureRBOs[0]);

        GLES32.glRenderbufferStorage(GLES32.GL_RENDERBUFFER, GLES32.GL_DEPTH_COMPONENT24, 512, 512);
        GLES32.glFramebufferRenderbuffer(GLES32.GL_FRAMEBUFFER, GLES32.GL_DEPTH_ATTACHMENT, GLES32.GL_RENDERBUFFER, captureRBOs[0]);

        //Set up CubaMap
        int[] envCubeMap = new int[1];
        GLES32.glGenTextures(1, envCubeMap, 0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_CUBE_MAP, envCubeMap[0]);
        for(int i = 1; i <= 6; i++){
            GLES32.glTexImage2D(GLES32.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GLES32.GL_RGB16F, 512, 512, 0, GLES32.GL_RGB, GLES32.GL_FLOAT, null);
        }
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);

        //Set up projection
        projection = Mat4.perspective((float)Math.toRadians(90.0f), 1.0f, 0, 100);
        cubeCapture[0] = Mat4.lookAt(new Vec3(0), new Vec3(1, 0, 0), new Vec3(0, -1, 0));
        cubeCapture[1] = Mat4.lookAt(new Vec3(0), new Vec3(-1, 0, 0), new Vec3(0, -1, 0));
        cubeCapture[2] = Mat4.lookAt(new Vec3(0), new Vec3(0, 1, 0), new Vec3(0, 0, 1));
        cubeCapture[3] = Mat4.lookAt(new Vec3(0), new Vec3(0, -1, 0), new Vec3(0, -0, -1));
        cubeCapture[4] = Mat4.lookAt(new Vec3(0), new Vec3(0, 0, 1), new Vec3(0, -1, 0));
        cubeCapture[5] = Mat4.lookAt(new Vec3(0), new Vec3(0, 0, -1), new Vec3(0, -1, 0));

        shaderProgram.use();
        //shaderProgram.setUniformInt(uniformLocations.get(0),0);
        //shaderProgram.setUniformMat4(uniformLocations.get(1), projection);
        GLES32.glActiveTexture(GLES32.GL_TEXTURE0);
        //GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, hdriTexture);



        GLES32.glViewport(0, 0,512,512);
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, captureFBOs[0]);
        for(int i = 0; i < 6; i++){
            //shaderProgram.setUniformMat4(uniformLocations.get(2), cubeCapture[i]);
            GLES32.glFramebufferTexture2D(GLES32.GL_FRAMEBUFFER, GLES32.GL_COLOR_ATTACHMENT0,
                    GLES32.GL_TEXTURE_CUBE_MAP_POSITIVE_X+1, envCubeMap[0], 0);
            GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
            renderCube();
        }
        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, 0);

    }

    public void addUniform(String uniformName){
        uniformLocations.add(GLES32.glGetUniformLocation(shaderProgram.getProgramId(),uniformName));
    }

    public void addHDRITexture(String filePath) throws IOException {
        FloatBuffer texture = ImageUtils.loadHDRIfromAssets(filePath, context);
        textureIDs.add(ImageUtils.createHDRI(texture));
    }

    public void drawFrame(){

    }

    public void renderCube(){
        if(vaoIDs[0]==0){
                                //back face
            float[] vertices = {-1.0f, -1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,
                                 1.0f,  1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f,
                                 1.0f, -1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  0.0f,
                                 1.0f,  1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  1.0f,  1.0f,
                                -1.0f, -1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,
                                -1.0f,  1.0f, -1.0f,  0.0f,  0.0f, -1.0f,  0.0f,  0.0f,
                                //front face
                                -1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f,
                                 1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  0.0f,
                                 1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f,
                                 1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,  1.0f,
                                -1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,  1.0f,
                                -1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,  0.0f,
                                //left face
                                -1.0f,  1.0f,  1.0f, -1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
                                -1.0f,  1.0f, -1.0f, -1.0f,  0.0f,  0.0f,  1.0f,  1.0f,
                                -1.0f, -1.0f, -1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
                                -1.0f, -1.0f, -1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
                                -1.0f, -1.0f,  1.0f, -1.0f,  0.0f,  0.0f,  0.0f,  0.0f,
                                -1.0f,  1.0f,  1.0f, -1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
                                //right face
                                 1.0f,  1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
                                 1.0f, -1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
                                 1.0f,  1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  1.0f,
                                 1.0f, -1.0f, -1.0f,  1.0f,  0.0f,  0.0f,  0.0f,  1.0f,
                                 1.0f,  1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  1.0f,  0.0f,
                                 1.0f, -1.0f,  1.0f,  1.0f,  0.0f,  0.0f,  0.0f,  0.0f,
                                //bottom face
                                -1.0f, -1.0f, -1.0f,  0.0f, -1.0f,  0.0f,  0.0f,  1.0f,
                                 1.0f, -1.0f, -1.0f,  0.0f, -1.0f,  0.0f,  1.0f,  1.0f,
                                 1.0f, -1.0f,  1.0f,  0.0f, -1.0f,  0.0f,  1.0f,  0.0f,
                                 1.0f, -1.0f,  1.0f,  0.0f, -1.0f,  0.0f,  1.0f,  0.0f,
                                -1.0f, -1.0f,  1.0f,  0.0f, -1.0f,  0.0f,  0.0f,  0.0f,
                                -1.0f, -1.0f, -1.0f,  0.0f, -1.0f,  0.0f,  0.0f,  1.0f,
                                //top face
                                -1.0f,  1.0f, -1.0f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f,
                                 1.0f,  1.0f,  1.0f,  0.0f,  1.0f,  0.0f,  1.0f,  0.0f,
                                 1.0f,  1.0f, -1.0f,  0.0f,  1.0f,  0.0f,  1.0f,  1.0f,
                                 1.0f,  1.0f,  1.0f,  0.0f,  1.0f,  0.0f,  1.0f,  0.0f,
                                -1.0f,  1.0f, -1.0f,  0.0f,  1.0f,  0.0f,  0.0f,  1.0f,
                                -1.0f,  1.0f,  1.0f,  0.0f,  1.0f,  0.0f,  0.0f,  0.0f
            };
            int[] indices = {0, 1, 2,
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
                    33, 34, 35};

            FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(vertices.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
            vertexBuffer.put(vertices).position(0);

            indexBuffer = ByteBuffer.allocateDirect(indices.length*4).order(ByteOrder.nativeOrder()).asIntBuffer();
            indexBuffer.put(indices).position(0);

            //storeDataInAttributeLis()
            GLES32.glGenBuffers(2, vboIDs, 0);
            GLES32.glBindBuffer(GLES32.GL_ARRAY_BUFFER, vboIDs[0]);
            vertexBuffer.position(0);
            GLES32.glBufferData(GLES32.GL_ARRAY_BUFFER,  vertices.length*4, vertexBuffer, GLES32.GL_STATIC_DRAW);
            //GLES32.glVertexAttribPointer(0,3, GLES32.GL_FLOAT, false , 0, vertexBuffer);
            GLES32.glBindBuffer(GLES32.GL_ELEMENT_ARRAY_BUFFER,vboIDs[1]);
            indexBuffer.position(0);
            GLES32.glBufferData(GLES32.GL_ELEMENT_ARRAY_BUFFER, indices.length*4, indexBuffer, GLES32.GL_STATIC_DRAW);

            GLES32.glGenVertexArrays(1, vaoIDs, 0);
            GLES32.glBindVertexArray(vaoIDs[0]);



        }
        //CANT RENDER WITH DRAWARRAYS! NEED TO DRAW WITH ATTRIBPOINTER
        GLES32.glBindVertexArray(vaoIDs[0]);
        GLES32.glDrawElements(GLES32.GL_TRIANGLES, 36, GLES32.GL_UNSIGNED_INT, 0);
        GLES32.glBindVertexArray(0);

    }

}
