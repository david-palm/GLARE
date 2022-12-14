package com.example.glare.ui;

import android.util.Log;

import com.example.glare.events.Event;
import com.example.glare.events.EventDispatcher;
import com.example.glare.events.TouchDownEvent;
import com.example.glare.events.TouchMoveEvent;
import com.example.glare.events.WindowResizeEvent;
import com.example.glare.layers.Layer;
import com.example.glare.ui.object2d.Object2D;

import java.util.ArrayList;
import java.util.List;

public class Component extends Layer {
    List<Object2D> objects;
    public Component(){
        objects = new ArrayList<Object2D>();
    }
    public Component(Object2D object){
        objects = new ArrayList<Object2D>();
        objects.add(object);
    }
    public Component(ArrayList<Object2D> objects){
        this.objects = objects;
    }

    public boolean isInside(float x, float y){
        for(Object2D object : objects){
            if(object.isInside(x, y)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onEvent(Event event){
        EventDispatcher dispatcher = new EventDispatcher(event);
        dispatcher.dispatch(Event.Type.TOUCH_DOWN, (Event e) -> (onTouchDownEvent((TouchDownEvent) e)));
        dispatcher.dispatch(Event.Type.TOUCH_MOVE, (Event e) -> (onTouchMoveEvent((TouchMoveEvent) e)));
        dispatcher.dispatch(Event.Type.WINDOW_RESIZE, (Event e) ->(onWindowResizeEvent((WindowResizeEvent) e)));
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
    }

    @Override
    public void onRender(){
        for(int i = 0; i < objects.size(); i++){
            objects.get(i).onRender();
        }
    }

    public boolean onTouchDownEvent(TouchDownEvent e){
        if(isInside(e.getX(), e.getY())){
            Log.d("Component","Component clicked");
            return true;
        }
        return false;
    }

    public boolean onTouchMoveEvent(TouchMoveEvent e){
        if(isInside(e.getX(), e.getY())){
            return true;
        }
        return false;
    }

    public boolean onWindowResizeEvent(WindowResizeEvent e){
        float aspectRatio = e.getX() / e.getY();
        for(Object2D object : objects){
            object.getMaterial().getShader().bind();
            object.getMaterial().getShader().setAspectRatio(aspectRatio);
        }
        return false;
    }
}
