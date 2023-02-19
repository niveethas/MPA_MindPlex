package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GamesMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_menu);
    }

    public void numberGameActivity(View view){
        Intent MMGames = new Intent(GamesMenu.this, numberGameActivity.class);
        startActivity(MMGames);
    }

    public void textGameActivity(View view){

    }

    public void pictureGameActivity(View view){
        Intent MMPicture = new Intent(GamesMenu.this, CameraGameActivity.class);
        startActivity(MMPicture);
    }
}