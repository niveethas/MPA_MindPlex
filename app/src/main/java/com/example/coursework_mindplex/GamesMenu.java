package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GamesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_games_menu);
    }

    public void numberGameActivity(View view){
        Intent MMGames = new Intent(GamesMenu.this, numberGameActivity.class);
        startActivity(MMGames);
    }

    public void textGameActivity(View view){
        Intent MMText = new Intent(GamesMenu.this, TextGameActivity.class);
        startActivity(MMText);
    }

    public void pictureGameActivity(View view){
        Intent MMPicture = new Intent(GamesMenu.this, CameraGameActivity.class);
        startActivity(MMPicture);
    }
}