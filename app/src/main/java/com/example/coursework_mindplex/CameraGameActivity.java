package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//this activity has been derived from: https://medium.com/swlh/introduction-to-androids-camerax-with-java-ca384c522c5

public class CameraGameActivity extends AppCompatActivity {
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;
    public List<String> colorPicker = Arrays.asList("Blue","White","Red","Green","Black","Pink","Orange","Yellow");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_game);
        Button enableCamera = findViewById(R.id.enableCamera);
        setColourText();
        enableCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasCameraPermission()) {
                    enableCamera();
                } else {
                    requestPermission();
                }
            }
        });
    }


    public void setColourText(){
        String colorChoice = colorPicker.get(randomColor());
        TextView colorText = findViewById(R.id.colourContainer);
        colorText.setText("Find the color, "+colorChoice+"!");
    }

    public int randomColor (){
        Random r = new Random();
        int low = 0;
        int high = 8;
        int result = r.nextInt(high-low) + low;

        return result;
    }

    private boolean hasCameraPermission() {
        return ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                CAMERA_PERMISSION,
                CAMERA_REQUEST_CODE
        );
    }

    private void enableCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }


}