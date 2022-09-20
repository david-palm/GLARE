package com.example.opengl3renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.opengl.GLES32;
import android.view.ScaleGestureDetector;

import com.example.opengl3renderer.events.Event;
import com.example.opengl3renderer.layers.Layer;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.scene.Scene;
import com.example.opengl3renderer.ui.Component;
import com.example.opengl3renderer.ui.object2d.card.Card;

import java.util.ArrayList;
import java.util.List;

public class AppRenderer extends ScaleGestureDetector.SimpleOnScaleGestureListener implements GLSurfaceView.Renderer {
    static final String TAG = "AppRenderer";

    int width;
    int height;

    Context context;
    List<Layer> layerStack = new ArrayList<Layer>();

    //SceneRenderer sceneRenderer;

    public AppRenderer(Context context){
        this.context = context;
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        layerStack.add(new Scene(new Vec2(width, height), context));
        // Creating UI test
        Card card = new Card(context);
        card.setScale(new Vec2(0.25f, 0.1f));
        layerStack.add(new Component(card));

        GLES32.glEnable(GLES32.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height) {
        this.width = width;
        this.height = height;
        //Set Viewport
        GLES32.glViewport(0,0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 glUnused) {
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

    public void onEvent(Event event) {
        for(int i = layerStack.size() - 1; i >= 0; i--) {
            layerStack.get(i).onEvent(event);
        }
    }

    public void addLayer(Layer layer) {
        layerStack.add(layer);
    }
}
