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
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_sign_in);
        mAuth = FirebaseAuth.getInstance();

    }

    public void signin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            uid = user.getUid();
                            Intent SIMenu = new Intent(SignInActivity.this, MainMenu.class);
                            SIMenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if (task.isSuccessful()) {
                                        DocumentSnapshot document = task.getResult();
                                        if (document.exists()) {
                                            String themeData = document.getString("Dark Mode");
                                            if (themeData.equals("true")) {
                                                Theme.Night = true;
                                            }else{
                                                Theme.Night = false;
                                            }

                                        }
                                    }
                                }
                            });
                            startActivity(SIMenu);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Sign In failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
        });
    }

    public void loginButtonClicked(View view) {
        EditText email = findViewById(R.id.emailTxtboxSI);
        EditText password = findViewById(R.id.passwordTxtboxSI);
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        signin(sEmail, sPassword);
    }

    public void cancelButtonClicked(View view){
        Intent SIBack = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(SIBack);
    }

    public void backBtnClick(View view){
        Intent backMM = new Intent(SignInActivity.this, MainActivity.class);
        backMM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backMM);

    }

    public void forgotPasswordSend(View view) {
        EditText email = findViewById(R.id.emailTxtboxSI);
        String sEmail = email.getText().toString();

        if (sEmail.isEmpty()) {
            Toast.makeText(SignInActivity.this, "Enter address to send reset email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            FirebaseAuth.getInstance().sendPasswordResetEmail(sEmail)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignInActivity.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SignInActivity.this, "No existing accounts with this email", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }
    }
}