package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.*;
import com.squareup.picasso.Picasso;


public class StressAdviceActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference storageRef;
    FirebaseStorage storage = FirebaseStorage.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_advice);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        storageRef = storage.getReference();
        ImageView imageView = findViewById(R.id.AdviceImage);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/6531.jpg?alt=media&token=ab19628c-5913-47c5-92c0-cd032c6c12ab").resize(600, 400).into(imageView);

    }

    public void getImageAsync(){




    }

}