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
    public List<Integer> usedANums = new ArrayList<>();
    public List<Integer> usedSNums = new ArrayList<>();
    public List<Integer> usedINums = new ArrayList<>();
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
        getAnxietyAdviceText();
        getStressAdviceText();
        getImages();
        displayTexts();
        displayImages();
    }


    public void getImages() {
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/4824.jpg?alt=media&token=3da2cc18-0a55-47e7-a262-684f98824f77");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/23548078_Human%20hand%20stretching%20to%20young%20unhappy%20girl.jpg?alt=media&token=fc0c721b-6db5-4a51-a62f-468a1124164a");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/6531.jpg?alt=media&token=ab19628c-5913-47c5-92c0-cd032c6c12ab");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/7774211_3744414.jpg?alt=media&token=0460c222-cedb-43c8-8f12-d000749d15e5");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/Happy%20women%20sitting%20and%20talking%20to%20each%20other.jpg?alt=media&token=d4515484-705b-4264-9c71-40dcffab9027");
        imagesURLs.add("https://firebasestorage.googleapis.com/v0/b/coursework-mindplex.appspot.com/o/Work_5.jpg?alt=media&token=bfb5dd8b-d88d-42a5-bfc1-0a81ec2fe6a1");
    }

    public void displayImages(){
        ImageView image2 = findViewById(R.id.StressImage);
        ImageView image1 = findViewById(R.id.AdviceImage);
        ImageView image3 = findViewById(R.id.infographicImage);
        Picasso.get().load(imagesURLs.get(imagePick())).resize(600, 400).into(image1);
        Picasso.get().load(imagesURLs.get(imagePick())).resize(600, 400).into(image2);
        Picasso.get().load(imagesURLs.get(imagePick())).resize(600, 400).into(image3);

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
                        String data = document.getString("Anxiety Advice");
                        Log.d("SuccessMsg:", data);
                        if (data == "true"){
                            anxietyAdviceToggle = true;
                        }else{
                            anxietyAdviceToggle = false;
                        }
                        TextView adviceText1 = findViewById(R.id.AnxietyAdvice1);
                        TextView adviceText2 = findViewById(R.id.AnxietyAdvice2);
                        TextView stressText1 = findViewById(R.id.StressText1);
                        TextView stressText2 = findViewById(R.id.StressText2);
                        TextView stressText3 = findViewById(R.id.StressText3);
                        try {

                            if (anxietyAdviceToggle = true) {
                                getAnxietyAdviceText();
                                stressText1.setText(stressAdvice[stressPick()]);
                                stressText2.setText(stressAdvice[stressPick()]);
                                stressText3.setText(stressAdvice[stressPick()]);

                                adviceText1.setText(anxietyAdvice[anxietyPick()]);
                                adviceText2.setText(anxietyAdvice[anxietyPick()]);

                            } else {
                                getStressAdviceText();
                                adviceText1.clearComposingText();
                                adviceText2.clearComposingText();

                                stressText1.setText(stressAdvice[stressPick()]);
                                stressText2.setText(stressAdvice[stressPick()]);
                                stressText3.setText(stressAdvice[stressPick()]);

                            }
                        }
                        catch (Exception e){
                            Log.w("StressAdvice", e);
                            }
                    } else {
                        Log.d("StressAdvice", "No such document");
                        anxietyAdviceToggle = true;
                    }
                } else {
                    Log.d("StressAdvice", "get failed with ",
                            task.getException());
                    anxietyAdviceToggle = true;
                    //The default of the anxiety advice is to be on.
                }
            }
        });



    }

    //the following randomiser functions have been derived from: https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values

    public int anxietyPick (){
        Random r = new Random();
        int low = 0;
        int highA = anxietyAdvice.length;
        int resultA = r.nextInt(highA-low) + low;
        if (usedANums.contains(resultA)) {
            return anxietyPick();
        }else {
            usedANums.add(resultA);
            return resultA;
        }
    }

    public int stressPick (){
        Random r = new Random();
        int low = 0;
        int highB = stressAdvice.length;
        int resultB = r.nextInt(highB-low) + low;
        if (usedSNums.contains(resultB)) {
            return stressPick();
        }else {
            usedSNums.add(resultB);
            return resultB;
        }
    }

    public int imagePick(){
        Random r = new Random();
        int low = 0;
        int highI = 6;
        int resultI = r.nextInt(highI-low) + low;
        if (usedINums.contains(resultI)) {
            return imagePick();
        }else {
            usedINums.add(resultI);
            return resultI;
        }
    }

}