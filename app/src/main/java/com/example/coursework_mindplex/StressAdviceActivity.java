package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class StressAdviceActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public String[] anxietyAdvice;
    public String[] stressAdvice;
    public List<String> imagesURLs = new ArrayList<>();
    public boolean anxietyAdviceToggle;
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
        displayTexts();
    }


    public void getImages() {
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/4824.jpg?alt=media&token=3da2cc18-0a55-47e7-a262-684f98824f77");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/23548078_Human%20hand%20stretching%20to%20young%20unhappy%20girl.jpg?alt=media&token=fc0c721b-6db5-4a51-a62f-468a1124164a");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/6531.jpg?alt=media&token=ab19628c-5913-47c5-92c0-cd032c6c12ab");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/7774211_3744414.jpg?alt=media&token=0460c222-cedb-43c8-8f12-d000749d15e5");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/Happy%20women%20sitting%20and%20talking%20to%20each%20other.jpg?alt=media&token=d4515484-705b-4264-9c71-40dcffab9027");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/Work_5.jpg?alt=media&token=bfb5dd8b-d88d-42a5-bfc1-0a81ec2fe6a1");
       // ImageView adviceText1 = findViewById(R.id.AnxietyAdvice1);
       // TextView adviceText2 = findViewById(R.id.AnxietyAdvice2);
    }

    public void getAnxietyAdviceText(){
        db.collection("Advice").document("Anxiety").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String data = document.getString("Text");
                        anxietyAdvice = data.split("/");

                    } else {
                        Log.d("MainActivity", "No such document");
                    }
                } else {
                    Log.d("MainActivity", "get failed with ",
                            task.getException());
                }
            }
        });

    }

    public void getStressAdviceText(){
        try {
            db.collection("Advice").document("Stress").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String data = document.getString("Text");
                            stressAdvice = data.split("/");
                        } else {
                            Log.d("MainActivity", "No such document");
                        }
                    } else {
                        Log.d("MainActivity", "get failed with ",
                                task.getException());
                    }
                }
            });
        } catch (Exception exception){
            Log.d("error niv:",exception.toString());

        }
    }

    public void displayTexts() {
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String data = document.getString("AnxietyAdvice");
                        if (data == "True"){
                            anxietyAdviceToggle = true;
                        }else{
                            anxietyAdviceToggle = false;
                        }
                    } else {
                        Log.d("MainActivity", "No such document");
                        anxietyAdviceToggle = true;
                    }
                } else {
                    Log.d("MainActivity", "get failed with ",
                            task.getException());
                    anxietyAdviceToggle = true;
                    //The default of the anxiety advice is to be on.
                }
            }
        });



        TextView adviceText1 = findViewById(R.id.AnxietyAdvice1);
        TextView adviceText2 = findViewById(R.id.AnxietyAdvice2);
        TextView stressText1 = findViewById(R.id.StressText1);
        TextView stressText2 = findViewById(R.id.StressText2);
        TextView stressText3 = findViewById(R.id.StressText3);


        if (anxietyAdviceToggle = true){
            getAnxietyAdviceText();
            getStressAdviceText();
            stressText1.setText(stressAdvice[stressPick()]);
            stressText2.setText(stressAdvice[stressPick()]);
            stressText3.setText(stressAdvice[stressPick()]);

            adviceText1.setText(stressAdvice[anxietyPick()]);
            adviceText2.setText(stressAdvice[anxietyPick()]);

        }else{
            getStressAdviceText();
            adviceText1.clearComposingText();
            adviceText2.clearComposingText();

            stressText1.setText(stressAdvice[stressPick()]);
            stressText2.setText(stressAdvice[stressPick()]);
            stressText3.setText(stressAdvice[stressPick()]);

        }
    }

    public int anxietyPick (){
        Random r = new Random();
        int low = 0;
        int highA = anxietyAdvice.length;
        int resultA = r.nextInt(highA-low) + low;
        return resultA;
    }

    public int stressPick (){
        Random r = new Random();
        int low = 0;
        int highB = stressAdvice.length;
        int resultB = r.nextInt(highB-low) + low;
        return resultB;
    }

    public int imagePick(){
        Random r = new Random();
        int low = 0;
        int highB = 6;
        int resultB = r.nextInt(highB-low) + low;
        return resultB;
    }

}