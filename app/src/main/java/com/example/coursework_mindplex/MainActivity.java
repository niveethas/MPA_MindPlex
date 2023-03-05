package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth startUpAuth;
    private FirebaseUser cachedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            cachedUser = startUpAuth.getCurrentUser();
            if (cachedUser != null){
                Intent MAMenu = new Intent(MainActivity.this, MainMenu.class);
                MAMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(MAMenu);
            }
        }
        catch (Exception e){
            Log.w("StartUp:",e);
        }
        setContentView(R.layout.activity_main);

    }

    public void SignIn(View view){
        Intent SIintent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(SIintent);
    }

    public void SignUp(View view){
        Intent SUintent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(SUintent);
    }

    
}