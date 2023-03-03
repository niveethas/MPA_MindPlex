package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainMenu extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_main_menu);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        //Customise text with current user's name
        try {
           db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
               @Override
               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                   if (task.isSuccessful()) {
                       DocumentSnapshot document = task.getResult();
                       if (document.exists()) {
                           String name = document.getString("First Name");
                           TextView intro = findViewById(R.id.introQText);
                           intro.setText("Hi "+ name +"! What would you like to do today?");
                       }
                   }
               }
           });
        }catch (Exception e) {
           TextView intro = findViewById(R.id.introQText);
           intro.setText("Hi, what would you like to do today?");
        }
    }


    public void gamesClicked(View view){
        Intent MMGames = new Intent(MainMenu.this, GamesMenu.class);
        startActivity(MMGames);
    }

    public void talkClicked(View view){
        Intent MMTalk = new Intent(MainMenu.this, CharityInfoActivity.class);
        startActivity(MMTalk);
    }

    public void stressManageClicked(View view){
        Intent MMStress = new Intent(MainMenu.this, StressAdviceActivity.class);
        startActivity(MMStress);
    }

    public void manageAccountClicked(View view){
        Intent MMAccount = new Intent(MainMenu.this, AccountMenu.class);
        startActivity(MMAccount);
    }




}