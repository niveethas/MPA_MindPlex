package com.example.coursework_mindplex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class CharityInfoActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public String uid;
    FirebaseFirestore db;
    public String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        uid = user.getUid();
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_charity_info);
        setCharityText();

    }

    public void setCharityText(){
        TextView charity1Text = findViewById(R.id.charityText1);
        TextView charity2Text = findViewById(R.id.charityText2);
        TextView charity1Title = findViewById(R.id.charityTitle1);
        TextView charity2Title = findViewById(R.id.charityTitle2);
        charity1Title.setText("Samaritans");
        charity1Text.setText("CALL 116 123 anytime for FREE ");
        charity2Title.setText("Crisis Text Line");
        charity2Text.setText("Text HOME to 741741 for 24/7 support at your fingertips");

        //setting text dynamically
    }

    public void openCharityInfo1(View view)
    {
        // This function has been derived from https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application
        Intent browserSamaritans = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.samaritans.org/how-we-can-help/contact-samaritan/talk-us-phone/"));
        startActivity(browserSamaritans);
    }

    public void openCharityInfo2(View view){
        // This function has been derived from https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application
        Intent browserCTL = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.crisistextline.org/"));
        startActivity(browserCTL);
    }

    public void openMapActivity(View view){
        db.collection("Users").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        status = document.getString("Location");
                    }
                    if (status.equals("true")) {
                        Intent mapActivity = new Intent(CharityInfoActivity.this, localCharityMapActivity.class);
                        startActivity(mapActivity);
                    } else {
                        Toast.makeText(CharityInfoActivity.this, "Please change location permissions to open!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void openCharityNum1(View view){
        Intent phoneSamaritians = new Intent(Intent.ACTION_DIAL);
        phoneSamaritians.setData(Uri.parse("tel:116 123"));
        startActivity(phoneSamaritians);

    }

    public void openCharityNum2(View view){
        Intent phoneCTL = new Intent(Intent.ACTION_VIEW);
        phoneCTL.setData(Uri.parse("tel:741741"));
        startActivity(phoneCTL);

    }

    public void backBtnClick(View view){
        Intent backMM = new Intent(CharityInfoActivity.this, MainMenu.class);
        backMM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backMM);

    }
}