package com.example.opengl3renderer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLES32;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.inputmethod.InputBinding;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

public class ImageUtils {
    public static Bitmap loadTextureFromAssets(String fileName, Context context){
        InputStream inputStream = null;

        try
        {
            inputStream = context.getAssets().open (fileName );
        }
        catch (IOException ioException)
        {
            inputStream = null;
            Log.e("ImageUtils", "Error reading Image File");
        }

        if (inputStream == null)
        {
            return null;
        }

        return BitmapFactory.decodeStream (inputStream);

    }

    public static int createTexture(Bitmap bitmap){
        int[] textureID = new int[1];

        GLES32.glGenTextures(1, textureID, 0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_2D, textureID[0]);

        GLUtils.texImage2D ( GLES32.GL_TEXTURE_2D, 0, bitmap, 0 );

        GLES32.glTexParameteri ( GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_LINEAR );
        GLES32.glTexParameteri ( GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR );
        GLES32.glTexParameteri ( GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE );
        GLES32.glTexParameteri ( GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE );
        GLES32.glTexParameteri ( GLES32.GL_TEXTURE_2D, GLES32.GL_TEXTURE_WRAP_R, GLES32.GL_CLAMP_TO_EDGE );

        return textureID[0];
    }


    public static int createHDRI(FloatBuffer data){
        int[] frameBufferID = new int[1];
        int[] renderBufferID = new int[1];

        GLES32.glBindFramebuffer(GLES32.GL_FRAMEBUFFER, frameBufferID[0]);
        GLES32.glBindRenderbuffer(GLES32.GL_RENDERBUFFER, renderBufferID[0]);
        GLES32.glRenderbufferStorage(GLES32.GL_RENDERBUFFER, GLES32.GL_DEPTH_COMPONENT24, 512, 512);
        GLES32.glFramebufferRenderbuffer(GLES32.GL_FRAMEBUFFER, GLES32.GL_DEPTH_ATTACHMENT, GLES32.GL_RENDERBUFFER, renderBufferID[0]);

        int[] hdriID = new int[1];
        GLES32.glGenTextures(1, hdriID, 0);
        GLES32.glBindTexture(GLES32.GL_TEXTURE_CUBE_MAP, hdriID[0]);
        for(int i = 0; i < 6; i++){
            GLES32.glTexImage2D(GLES32.GL_TEXTURE_CUBE_MAP_POSITIVE_X + i, 0, GLES32.GL_RGB16F, 512, 512, 0, GLES32.GL_RGB, GLES32.GL_FLOAT, data);
        }

        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_WRAP_S, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_WRAP_T, GLES32.GL_CLAMP_TO_EDGE);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_MIN_FILTER, GLES32.GL_LINEAR);
        GLES32.glTexParameteri(GLES32.GL_TEXTURE_CUBE_MAP, GLES32.GL_TEXTURE_MAG_FILTER, GLES32.GL_LINEAR);
        return hdriID[0];
    }
}
