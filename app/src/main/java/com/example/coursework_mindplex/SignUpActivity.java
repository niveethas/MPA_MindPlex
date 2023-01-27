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

    //auth.getcurrentid

    //****** Use authentication to log user in and then use the unique id to get the user information from db ******
    public void signup(String email, String password, String firstName, String lastName){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult>
                                                           task) {
                                if (task.isSuccessful()) {
                                    Log.d("MainActivity",
                                            "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = user.getUid();
                                    //****TO-DO: add activity ******
                                    Map<String,Object> userDetails = new HashMap<>();
                                    userDetails.put("First Name", firstName);
                                    userDetails.put("Last Name",lastName);
                                    userDetails.put("Anxiety Advice", "true");
                                    userDetails.put("Camera", "true");
                                    userDetails.put("Dark Mode", "false");
                                    userDetails.put("Location", "true");
                                    db.collection("Users").document(uid).set(userDetails)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Log.d("SignUpActivity","User written");
                                                }
                                            })   ;
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
        EditText fname = findViewById(R.id.FNameTxtbox);
        EditText lname = findViewById(R.id.LNameTxtbox);
        String sEmail = email.getText().toString();
        String sPassword = password.getText().toString();
        String sFName = fname.getText().toString();
        String sLName = lname.getText().toString();
        Log.d("Sign in: ", sEmail + sPassword + sFName + sLName);
        signup(sEmail, sPassword, sFName, sLName);
    }


}