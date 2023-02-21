package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CharityInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_info);

    }

    public void setCharityText(){
        TextView charity1Text = findViewById(R.id.charityText1);
        TextView charity2Text = findViewById(R.id.charityText2);
        TextView charity1Title = findViewById(R.id.charityTitle1);
        charity1Title.setText("Samaritans");
        charity1Text.setText("CALL 116 123 anytime for FREE");
    }

    public void openCharityInfo1(View view)
    {
        // This function has been derived from https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }
}