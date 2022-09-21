package com.example.opengl3renderer.renderer;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.opengl3renderer.utils.ImageUtils;

public class Texture {
    String name;
    String filePath;
    int id;

    public Texture(Context context, String fileName){
        this.name = "unnamed texture";
        filePath = "textures/" + fileName + ".jpg";
        Bitmap texture = ImageUtils.loadTextureFromAssets(filePath, context);
        id = (ImageUtils.createTexture(texture));
    }

    public Texture(String name, String filePath, Context context){
        this.name = name;
        this.filePath = filePath;
        Bitmap texture = ImageUtils.loadTextureFromAssets(filePath, context);
        id = (ImageUtils.createTexture(texture));
    }

    public int getId(){
        return id;
    }
}
