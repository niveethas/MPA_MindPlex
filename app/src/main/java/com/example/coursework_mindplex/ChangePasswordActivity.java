package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText newPassword;
    EditText newPasswordR;
    EditText oldPassword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String uEmail;
    public String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$";
    Pattern passwordValidation = Pattern.compile(passwordRegex);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_change_password);
        newPassword = findViewById(R.id.newPasswordText);
        newPasswordR = findViewById(R.id.newPasswordTextR);
        uEmail = user.getEmail();
    }

//function derived from https://stackoverflow.com/questions/39866086/change-password-with-firebase-for-android#:~:text=One%20way%20to%20allow%20your,updatePassword()%20to%20update%20it.
    public void changePassword (View view){
        Matcher validationChecker = passwordValidation.matcher(String.valueOf(newPasswordR.getText()));
        if (validationChecker.matches()){
            if (String.valueOf(newPasswordR.getText()).equals(String.valueOf(newPassword.getText()))){
            oldPassword = findViewById(R.id.oldPasswordText);
            if(oldPassword.equals(String.valueOf(newPasswordR.getText()))) {
                Toast.makeText(ChangePasswordActivity.this, "Please choose a different password to your current one.", Toast.LENGTH_SHORT).show();
            }else {
                try {
                    AuthCredential credential = EmailAuthProvider.getCredential(uEmail, String.valueOf(oldPassword.getText()));
                    user.reauthenticate(credential)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        user.updatePassword(String.valueOf(newPassword.getText())).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ChangePasswordActivity.this, "New Password Saved!", Toast.LENGTH_LONG).show();
                                                    Intent backAM = new Intent(ChangePasswordActivity.this, AccountMenu.class);
                                                    backAM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    startActivity(backAM);
                                                } else {
                                                    Toast.makeText(ChangePasswordActivity.this, "Password Change Failed.", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(ChangePasswordActivity.this, "Old is password incorrect!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                } catch (Exception e) {
                    Log.w("ChangePassword", e);
                    Toast.makeText(ChangePasswordActivity.this, "Password Change Failed.", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
                Toast.makeText(ChangePasswordActivity.this, "New passwords do not match!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(ChangePasswordActivity.this, "New password does not meet requirements.", Toast.LENGTH_SHORT).show();
        }

    }

    public void backBtnClick(View view){
        Intent backAM = new Intent(ChangePasswordActivity.this, AccountMenu.class);
        backAM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backAM);

    }

}