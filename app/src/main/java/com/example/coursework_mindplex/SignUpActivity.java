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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }


    //****** Use authentication to log user in and then use the unique id to get the user information from db ******
    public void signup(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult>
                                                           task) {
                                if (task.isSuccessful()) {
                                    Log.d("MainActivity",
                                            "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //****TO-DO: add activity ******
                                    Intent SUMenu = new Intent(SignUpActivity.this, MainMenu.class);
                                    startActivity(SUMenu);
                                } else {
                                    // If sign in fails, display a message to the user.
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
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        signup(sEmail, sPassword);
    }


}