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
        charity2Text.setText("Text HOME to 741741 for 24/7 support at your finger tips");

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
}