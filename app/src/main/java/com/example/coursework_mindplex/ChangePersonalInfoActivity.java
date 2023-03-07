package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChangePersonalInfoActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public String uId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_change_personal_info);
        uId = user.getUid();
        setInfoText();
    }

    public void setInfoText(){
        EditText newFname = findViewById(R.id.editFNameTxt);
        EditText newLname = findViewById(R.id.editLNameTxt);

        //Customise text with current user's name
        try {
            db.collection("Users").document(uId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = document.getString("First Name");
                            String surname = document.getString("Last Name");
                            newFname.setText(name);
                            newLname.setText(surname);
                        }
                    }
                }
            });
        }catch (Exception e) {
            Log.w("Change Activity",e);
        }

    }


    public void changePersonalInfo(View view){
        EditText newFname = findViewById(R.id.editFNameTxt);
        EditText newLname = findViewById(R.id.editLNameTxt);

        if (!toString().valueOf(newFname.getText()).isEmpty()) {
            db.collection("Users").document(uId)
                    .update("First Name", toString().valueOf(newFname.getText()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ChangePersonalInfoActivity.this, "First Name Updated", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Personalisation", "Error updating document", e);
                        }
                    });

        }
        if (!toString().valueOf(newLname.getText()).isEmpty()) {
            db.collection("Users").document(uId)
                    .update("Last Name", toString().valueOf(newLname.getText()))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(ChangePersonalInfoActivity.this, "Last Name Updated", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("Personalisation", "Error updating document", e);
                        }
                    });
        }
    }

    public void backBtnClick(View view){
        Intent backAM = new Intent(ChangePersonalInfoActivity.this, AccountMenu.class);
        backAM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backAM);

    }
}