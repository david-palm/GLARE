package com.example.opengl3renderer.inertia;

import java.util.Timer;

public class Inertia {
    long startTime;
    long duration;
    Function function;

    public Inertia(){
    }

    public Inertia(long duration, Function function){
        this.duration = duration;
        this.function = function;
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public void start(long duration){
        this.duration = duration;
        startTime = System.currentTimeMillis();
    }

    public float getValue(){
        long time = System.currentTimeMillis();
        if(time > startTime + duration){
            return 0;
        }
        return function.getValue(time - startTime);
    }

    public float getStartTime() {
        return startTime;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }
}
