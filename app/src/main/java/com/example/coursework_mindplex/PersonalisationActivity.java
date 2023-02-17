package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PersonalisationActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalisation);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
    }

    public void updateLocation(View view){

        Switch locationToggle = findViewById(R.id.locationSwitch);
        String newValue;
        boolean checked = locationToggle.isChecked();
        if (checked){
            newValue = "true";
        }else{
            newValue = "false";
        }
        db.collection("Users").document(uid)
                .update("Location", newValue)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }

    public void updateAdvice(View view){

        Switch adviceToggle = findViewById(R.id.anxietyAdviceSwitch);
        String newValue;
        boolean checked = adviceToggle.isChecked();
        if (checked){
            newValue = "true";
        }else{
            newValue = "false";
        }
        db.collection("Users").document(uid)
                .update("Anxiety Advice", newValue)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }

    public void updateTheme(View view){

        Switch themeToggle = findViewById(R.id.darkModeSwitch);
        String newValue;
        boolean checked = themeToggle.isChecked();
        if (checked){
            newValue = "true";
        }else{
            newValue = "false";
        }
        db.collection("Users").document(uid)
                .update("Dark Mode", newValue)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }

    public void updateCamera(View view){

        Switch cameraToggle = findViewById(R.id.cameraSwitch);
        String newValue;
        boolean checked = cameraToggle.isChecked();
        if (checked){
            newValue = "true";
        }else{
            newValue = "false";
        }
        db.collection("Users").document(uid)
                .update("Camera", newValue)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("MainActivity", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("MainActivity", "Error updating document", e);
                    }
                });
    }

}