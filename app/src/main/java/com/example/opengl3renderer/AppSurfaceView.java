package com.example.opengl3renderer;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import com.example.opengl3renderer.events.ScaleEvent;
import com.example.opengl3renderer.events.TouchDownEvent;
import com.example.opengl3renderer.events.TouchMoveEvent;
import com.example.opengl3renderer.events.TouchUpEvent;
import com.example.opengl3renderer.math.Vec2;

public class AppSurfaceView extends GLSurfaceView {
    AppRenderer appRenderer;
    Vec2 prevCoordinate;
    ScaleGestureDetector scaleGestureDetector;

    public AppSurfaceView(Context context){
        super(context);
        setEGLContextClientVersion(3);
        setPreserveEGLContextOnPause(true);
        appRenderer = new AppRenderer(context);
        setRenderer(appRenderer);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    public AppSurfaceView(Context context, AttributeSet attrs){
        super(context, attrs);
        setEGLContextClientVersion(3);
        setPreserveEGLContextOnPause(true);
        appRenderer = new AppRenderer(context);
        setRenderer(appRenderer);
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        Vec2 deviceCoordinate = convertToDeviceCoordinates(new Vec2(e.getX(), e.getY()), new Vec2(appRenderer.width, appRenderer.height));
        scaleGestureDetector.onTouchEvent(e);
        if(scaleGestureDetector.isInProgress()){
            return true;
        }
        if(e.getPointerCount() > 1){
            return true;
        }
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                appRenderer.onEvent(new TouchDownEvent(deviceCoordinate.x, deviceCoordinate.y));
                prevCoordinate = deviceCoordinate;
                return true;
            case MotionEvent.ACTION_MOVE:
                appRenderer.onEvent(new TouchMoveEvent(deviceCoordinate.x, deviceCoordinate.y, prevCoordinate.x, prevCoordinate.y));
                prevCoordinate = deviceCoordinate;
                return true;
            case MotionEvent.ACTION_UP:
                appRenderer.onEvent(new TouchUpEvent(deviceCoordinate.x, deviceCoordinate.y));
                return true;
        }
        return true;
    }

    static private Vec2 convertToDeviceCoordinates(Vec2 coordinate, Vec2 resolution){
        float x = coordinate.x / resolution.x * 2.0f - 1.0f;
        float y = coordinate.y / resolution.y * - 2.0f + 1.0f;
        return new Vec2(x, y);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector){
            appRenderer.onEvent(new ScaleEvent(detector.getFocusX(), detector.getFocusY(), detector.getPreviousSpanX(), detector.getCurrentSpanY(), detector.getScaleFactor()));
            return true;
        }
    }
}
