package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
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
        Intent MMAccount = new Intent(MainMenu.this, PersonalisationActivity.class);
        startActivity(MMAccount);
    }
}