package com.example.glare.scene;

import android.content.Context;

import com.example.glare.events.Event;
import com.example.glare.events.EventDispatcher;
import com.example.glare.events.ScaleEvent;
import com.example.glare.events.TouchDownEvent;
import com.example.glare.events.TouchMoveEvent;
import com.example.glare.events.TouchUpEvent;
import com.example.glare.events.WindowResizeEvent;
import com.example.glare.layers.Layer;
import com.example.glare.math.Vec3;
import com.example.glare.math.Vec4;
import com.example.glare.renderer.Mesh;
import com.example.glare.renderer.Texture;
import com.example.glare.scene.object3d.Cube;
import com.example.glare.scene.object3d.Object3D;
import com.example.glare.scene.object3d.StandardMaterial3D;
import com.example.glare.scene.object3d.StandardObject3DShader;

import java.util.Timer;
import java.util.TimerTask;

public class Scene extends Layer {
    float aspectRatio;
    Camera camera;
    PointLight[] pointLights;
    Vec3 ambientLight;
    Object3D object;
    boolean blockRotating;
    Timer timer;

    public Scene(Context context){
        // Creating camera and lights
        camera = new Camera(new Vec3( 0.0f, 0.0f, -5.0f), 60.0f, 1.0f);
        pointLights = new PointLight[1];
        pointLights[0] = new PointLight(new Vec3(1.0f, 0.0f, 1.0f), new Vec3(1.0f, 1.0f, 1.0f), 1.25f);
        ambientLight = new Vec3(0.25f, 0.25f, 0.25f);

        // Creating a cube
        // Creating mesh
        Mesh mesh = new Cube();
        // Setting up textures
        Texture albedo = new Texture(context, "albedo");
        Texture normal = new Texture(context, "normal");
        Texture roughness = new Texture(context, "roughness");
        // Creating material
        StandardMaterial3D material = new StandardMaterial3D(new StandardObject3DShader(context));
        material.setAlbedo(albedo);
        material.setNormal(normal);
        material.setRoughness(roughness);
        material.setTextured(true);
        // Creating Object
        object = new Object3D(mesh, material);
        blockRotating = false;
        timer = new Timer();
    }

    public float getAspectRatio(){
        return aspectRatio;
    }

    public void setAspectRatio(float aspectRatio){
        this.aspectRatio = aspectRatio;
    }

    public void onEvent(Event event){
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.TOUCH_DOWN, (Event e) -> (onTouchDownEvent((TouchDownEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_MOVE, (Event e) -> (onTouchMoveEvent((TouchMoveEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_UP, (Event e) -> (onTouchUpEvent((TouchUpEvent) e)));
        dispatcher.dispatch(Event.Type.SCALE_GESTURE, (Event e) -> (onScaleEvent((ScaleEvent) e)));
        dispatcher.dispatch(Event.Type.WINDOW_RESIZE, (Event e) -> (onWindowResizeEvent((WindowResizeEvent) e)));
    }

    public void onRender(){
        object.onRender(this);
    }

    public void onUpdate(){
        object.onUpdate();
        camera.onUpdate();
    }

    // Returns true if blocking
    public boolean onTouchDownEvent(TouchDownEvent e){
        return true;
    }
    public boolean onTouchUpEvent(TouchUpEvent e){
        object.setRotating(false);
        object.startDeceleration();
        camera.startDeceleration();
        // Blocking rotation for 0.05 seconds
        timer.schedule(new TimerTask(){ public void run(){ blockRotating = false; }}, 50);
        return true;
    }
    public boolean onTouchMoveEvent(TouchMoveEvent e){
        if(!blockRotating) {
            object.setRotating(true);
            rotateObject(e.getDX(), e.getDY());
        }
        return true;
    }
    public boolean onScaleEvent(ScaleEvent e){
        zoom(e.getScaleFactor());
        blockRotating = true;
        return true;
    }

    public boolean onWindowResizeEvent(WindowResizeEvent e){
        aspectRatio = e.getX() / e.getY();
        camera.setAspectRatio(aspectRatio);
        return false;
    }

    public void rotateObject(float dX, float dY){
        float rotationSpeed = 2.5f * camera.getFov();
        float degToRad = (float) (Math.PI / 180.0);
        float rotX = dX * rotationSpeed * degToRad;
        float rotY = -dY * rotationSpeed * degToRad;
        object.rotate(new Vec4(0.0f, 1.0f, 0.0f, rotX));
        object.rotate(new Vec4(1.0f, 0.0f, 0.0f, rotY));
        object.setRotationVelocity(new Vec3(rotX, rotY, 0.0f));
    }

    public void zoom(float scaleFactor){
        float zoomSpeed = 1.0f;
        float fov = camera.getFov();
        if(scaleFactor != 0.0f)
            fov /= scaleFactor * zoomSpeed;
        camera.setFovVelocity(fov - camera.getFov());
        camera.setFov(fov);
    }

    public Camera getCamera(){
        return camera;
    }

    public void setCamera(Camera camera){
        this.camera = camera;
    }

    public PointLight[] getPointLights(){
        return pointLights;
    }

    public Vec3[] getPointLightPositions(){
        Vec3[] lightPositions = new Vec3[1];
        for(int i = 0; i < pointLights.length; i++){
            lightPositions[i] = pointLights[i].getPosition();
        }
        return lightPositions;
    }

    public Vec3[] getPointLightColors(){
        Vec3[] lightColors = new Vec3[1];
        for(int i = 0; i < pointLights.length; i++){
            if(pointLights[i] != null) {
                lightColors[i] = pointLights[i].getColor();
            }
        }
        return lightColors;
    }

    public void setPointLight(PointLight pointLight, int index){
        pointLights[index] = pointLight;
    }

    public Vec3 getAmbientLight(){
        return ambientLight;
    }

    public Object3D getObject(){
        return object;
    }

    public void setObject(Object3D object){
        this.object = object;
    }


}
