package com.example.opengl3renderer.object3d;

import android.content.Context;
import android.util.Log;

import com.example.opengl3renderer.events.Event;
import com.example.opengl3renderer.events.EventDispatcher;
import com.example.opengl3renderer.events.ScaleEvent;
import com.example.opengl3renderer.events.TouchDownEvent;
import com.example.opengl3renderer.events.TouchMoveEvent;
import com.example.opengl3renderer.events.TouchUpEvent;
import com.example.opengl3renderer.layers.Layer;
import com.example.opengl3renderer.math.Vec2;
import com.example.opengl3renderer.math.Vec3;
import com.example.opengl3renderer.math.Vec4;
import com.example.opengl3renderer.renderer.Mesh;
import com.example.opengl3renderer.renderer.Texture;

public class Scene extends Layer {
    Camera camera;
    Light[] lights;
    Object3D object;

    public Scene(Vec2 aspectRatio, Context context){
        // Creating camera and lights
        camera = new Camera(new Vec3( 0.0f, 0.0f, -5.0f), 60.0f, 9.0f / 16.0f);
        lights = new Light[1];
        lights[0] = new Light(new Vec3(1.0f, 0.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), 1.0f);

        // Creating a cube
        // Creating mesh
        Mesh mesh = new Cube();
        // Setting up textures
        Texture albedo = new Texture(context, "albedo");
        Texture normal = new Texture(context, "normal");
        Texture roughness = new Texture(context, "roughness");
        // Creating material
        StandardMaterial3D material = new StandardMaterial3D(new StandardObjectShader(context));
        material.setAlbedo(albedo);
        material.setNormal(normal);
        material.setRoughness(roughness);
        material.setTextured(true);
        // Creating Object
        object = new Object3D(mesh, material);
    }

    public void onEvent(Event event) {
        EventDispatcher dispatcher = new EventDispatcher(event);
        //dispatcher.dispatch(event.getType(), (Event e) -> (onTouchEvent((TouchEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_DOWN, (Event e) -> (onTouchDownEvent((TouchDownEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_MOVE, (Event e) -> (onTouchMoveEvent((TouchMoveEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_UP, (Event e) -> (onTouchUpEvent((TouchUpEvent) e)));
        dispatcher.dispatch(Event.Type.SCALE_GESTURE, (Event e) -> (onScaleEvent((ScaleEvent) e)));
    }

    public void onRender(){
        object.render(this);
    }

    // Returns true if blocking
    public boolean onTouchDownEvent(TouchDownEvent e) {
        return true;
    }
    public boolean onTouchUpEvent(TouchUpEvent e) {
        return true;
    }
    public boolean onTouchMoveEvent(TouchMoveEvent e) {
        rotateObject(e.getDX(), e.getDY());
        return true;
    }
    public boolean onScaleEvent(ScaleEvent e) {
        Log.d("Scene", "Scalefactor: " + e.getScaleFactor());
        float zoomSpeed = 1.0f;
        camera.setFov(camera.getFov() / e.getScaleFactor() * zoomSpeed);
        return true;
    }

    public void rotateObject(float dX, float dY) {
        float rotationSpeed = 150.0f;
        float degToRad = (float) (Math.PI/180.0);
        float rotX = dX * rotationSpeed * degToRad;
        float rotY = dY * rotationSpeed * degToRad;
        Log.d("Layer", rotX + ", " + rotY);
        object.rotate(new Vec4(0.0f, 1.0f, 0.0f, rotX));
        object.rotate(new Vec4(1.0f, 0.0f, 0.0f, rotY));
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Light[] getLights() {
        return lights;
    }

    public Vec3[] getLightPositions(){
        Vec3[] lightPositions = new Vec3[1];
        for(int i = 0; i < lights.length; i++){
            lightPositions[i] = lights[i].getPosition();
        }
        return lightPositions;
    }

    public Vec3[] getLightColors(){
        Vec3[] lightColors = new Vec3[1];
        for(int i = 0; i < lights.length; i++){
            if(lights[i] != null) {
                lightColors[i] = lights[i].getColor();
            }
        }
        return lightColors;
    }

    public void setLight(Light light, int index) {
        lights[index] = light;
    }

    public Object3D getObject() {
        return object;
    }

    public void setObject(Object3D object) {
        this.object = object;
    }
}
