package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//this activity has been derived from: https://medium.com/swlh/introduction-to-androids-camerax-with-java-ca384c522c5

public class CameraGameActivity extends AppCompatActivity {
    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;
    public List<String> colorPicker = Arrays.asList("Blue","White","Red","Green","Black","Pink","Orange","Yellow");
    private FirebaseAuth mAuth;
    public String uid;
    public String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_camera_game);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Button enableCamera = findViewById(R.id.enableCamera);
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        setColourText();
        enableCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                status = document.getString("Camera");
                            }
                            if (status.equals("true")) {
                                if (hasCameraPermission()) {
                                    enableCamera();
                                } else {
                                    requestPermission();
                                }
                            }else{
                                Toast.makeText(CameraGameActivity.this, "Please change camera permissions to play!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

    public void backBtnClick(View view){
        Intent backGM = new Intent(CameraGameActivity.this, GamesMenu.class);
        backGM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backGM);

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