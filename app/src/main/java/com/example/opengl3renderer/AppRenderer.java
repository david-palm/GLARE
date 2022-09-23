package com.example.opengl3renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES32;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.ScaleGestureDetector;

import com.example.opengl3renderer.events.Event;
import com.example.opengl3renderer.events.WindowResizeEvent;
import com.example.opengl3renderer.layers.Layer;
import com.example.opengl3renderer.scene.Scene;
import com.example.opengl3renderer.ui.UI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppRenderer extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GLSurfaceView.Renderer{

    int width;
    int height;

    Context context;
    List<Layer> layerStack = new ArrayList<Layer>();
    Timer timer;



    public AppRenderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig){
        layerStack.add(new Scene(context));
        // Creating UI test
        UI ui = new UI(context);
        ui.addToLayerStack(layerStack);
        GLES32.glEnable(GLES32.GL_DEPTH_TEST);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){ public void run(){onUpdate();}}, 0, 16);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height){
        this.width = width;
        this.height = height;
        onEvent(new WindowResizeEvent(width, height));
        // Set Viewport
        GLES32.glViewport(0,0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 glUnused){
        GLES32.glViewport(0,0,width, height);
        // Rendering background
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT | GLES32.GL_DEPTH_BUFFER_BIT);
        GLES32.glClearColor(0.02f, 0.02f, 0.02f, 1.0f);
        // Rendering frame layer by layer
        for(int i = 0; i < layerStack.size(); i++) {
            layerStack.get(i).onRender();
            GLES32.glClear( GLES32.GL_DEPTH_BUFFER_BIT);
        }
    }

    public void onEvent(Event event){
        for(int i = layerStack.size() - 1; i >= 0; i--) {
            layerStack.get(i).onEvent(event);
        }
    }

    public void onUpdate(){
        for(int i = layerStack.size() - 1; i >= 0; i--) {
            layerStack.get(i).onUpdate();
        }
    }

    public void addLayer(Layer layer){
        layerStack.add(layer);
    }
}
