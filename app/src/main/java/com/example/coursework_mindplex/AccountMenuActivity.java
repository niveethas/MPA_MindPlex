package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountMenuActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_menu);
        mAuth = FirebaseAuth.getInstance();
    }

    public void passwordChangeActivity(View view){
        Intent AMPassword = new Intent(AccountMenuActivity.this, ChangePasswordActivity.class);
        startActivity(AMPassword);
    }

    public void personalisationActivity(View view){
        Intent AMPersonalisation = new Intent(AccountMenuActivity.this, PersonalisationActivity.class);
        startActivity(AMPersonalisation);
    }

    public void signOutActivity(View view){
        mAuth.signOut();
        Intent AMSignOut = new Intent(AccountMenuActivity.this, MainActivity.class);
        startActivity(AMSignOut);
    }
}