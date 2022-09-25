package com.example.glare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {
    AppSurfaceView appSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        appSurfaceView = (AppSurfaceView) findViewById(R.id.appSurfaceView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        appSurfaceView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        appSurfaceView.onPause();
    }
}