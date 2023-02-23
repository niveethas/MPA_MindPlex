package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    //**** ADD ON CREATE METHOD *****


    public void SignIn(View view){
        Intent SIintent = new Intent(MainActivity.this, SignInActivity.class);
        startActivity(SIintent);
    }

    public void SignUp(View view){
        Intent SUintent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(SUintent);
    }
}