package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccountMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_menu);
    }

    public void passwordChangeActivity(View view){
        Intent AMPassword = new Intent(AccountMenuActivity.this, ChangePasswordActivity.class);
        startActivity(AMPassword);
    }

    public void personalisationActivity(View view){
        Intent AMPersonalisation = new Intent(AccountMenuActivity.this, PersonalisationActivity.class);
        startActivity(AMPersonalisation);
    }
}