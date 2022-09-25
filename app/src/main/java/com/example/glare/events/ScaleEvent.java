package com.example.glare.events;

public class ScaleEvent extends TouchEvent {
    float spanX;
    float spanY;
    float scaleFactor;
    public ScaleEvent(float x, float y, float spanX, float spanY, float scaleFactor){
        super(x, y, Type.SCALE_GESTURE);
        this.spanX = spanX;
        this.spanY = spanY;
        this.scaleFactor = scaleFactor;
    }

    public float getSpanX(){
        return spanX;
    }

    public float getSpanY(){
        return spanY;
    }

    public float getScaleFactor(){
        return scaleFactor;
    }
}
