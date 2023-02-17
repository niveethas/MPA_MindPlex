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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();

    }

// The following code has been derived from LAB 10 and LAB 11
    public void signup(String email, String password, String firstName, String lastName){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult>
                                                           task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    try {
                                        String uid = user.getUid();
                                        Map<String, Object> userDetails = new HashMap<>();
                                        userDetails.put("First Name", firstName);
                                        userDetails.put("Last Name", lastName);
                                        userDetails.put("Anxiety Advice", "true");
                                        userDetails.put("Camera", "true");
                                        userDetails.put("Dark Mode", "false");
                                        userDetails.put("Location", "true");
                                        db.collection("Users").document(uid).set(userDetails)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Log.d("SignUpActivity", "User written");
                                                    }
                                                });
                                        Intent SUMenu = new Intent(SignUpActivity.this, MainMenu.class);
                                        startActivity(SUMenu);
                                    }
                                    catch (Exception e) {
                                        //REMOVE THE USER FROM AUTHENTICATION IF IT COULD NOT BE SAVED IN FIRESTORE
                                        try {
                                            user.delete();
                                            Toast.makeText(SignUpActivity.this,
                                                    "Sign Up failed.",
                                                    Toast.LENGTH_SHORT).show();
                                        }catch (Exception ex){
                                            Log.w("SignUpActivity",
                                                    "deleteUser:failure", task.getException());
                                        }
                                    }
                                } else {
                                    // Log failure and send user a pop up to notify failure
                                    Log.w("MainActivity",
                                            "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this,
                                            "Sign Up failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
        });
    }

    public void registrationButtonClicked(View view) {
        EditText email = findViewById(R.id.emailTxtbox);
        EditText password = findViewById(R.id.passwordTxtbox);
        EditText rpassword = findViewById(R.id.RpasswordTxtbox);
        EditText fname = findViewById(R.id.FNameTxtbox);
        EditText lname = findViewById(R.id.LNameTxtbox);
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        String sFName = fname.getText().toString();
        String sLName = lname.getText().toString();
        String sRPassword = rpassword.getText().toString();
        if (sRPassword.equals(sPassword)) {
            signup(sEmail, sPassword, sFName, sLName);
        } else {
            Toast.makeText(SignUpActivity.this,
                    "Passwords Do Not Match.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void SUCancelButtonClicked(View view){
            Intent SUBack = new Intent(SignUpActivity.this, MainActivity.class);
            startActivity(SUBack);
    }



}