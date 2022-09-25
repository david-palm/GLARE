package com.example.glare.events;

public class Event {
    public enum Type{
        // ACTION_DOWN
        TOUCH_DOWN,
        // ACTION_MOVE
        TOUCH_MOVE,
        // ACTION_UP
        TOUCH_UP,
        // SCALE_GESTURE
        SCALE_GESTURE,
        // ACTION_CANCEL
        ACTION_CANCEL,
        // ACTION_POINTER_DOWN
        MULTITOUCH_DOWN,
        // ACTION_POINTER_UP
        MULTITOUCH_UP,
        // onSurfaceChange
        WINDOW_RESIZE,

        WINDOW_EVENT,
        APPLICATION_EVENT
    }

    private Type type;
    boolean handled;

    protected Event(Type type){
        this.type = type;
    }

    public Type getType(){
        return type;
    }
}
