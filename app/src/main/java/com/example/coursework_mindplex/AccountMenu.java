package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class AccountMenu extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_account_menu);
        mAuth = FirebaseAuth.getInstance();
    }

    public void passwordChangeActivity(View view){
        Intent AMPassword = new Intent(AccountMenu.this, ChangePasswordActivity.class);
        startActivity(AMPassword);
    }

    public void personalisationActivity(View view){
        Intent AMPersonalisation = new Intent(AccountMenu.this, PersonalisationActivity.class);
        AMPersonalisation.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(AMPersonalisation);
    }

    public void personalInfoActivity(View view){
        Intent AMpersonalInfo = new Intent(AccountMenu.this, ChangePersonalInfoActivity.class);
        startActivity(AMpersonalInfo);
    }

    public void signOutActivity(View view){
        mAuth.signOut();
        Intent AMSignOut = new Intent(AccountMenu.this, MainActivity.class);
        AMSignOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(AMSignOut);
    }

    public void backBtnClick(View view){
        Intent backMM = new Intent(AccountMenu.this, MainMenu.class);
        backMM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backMM);

    }
}