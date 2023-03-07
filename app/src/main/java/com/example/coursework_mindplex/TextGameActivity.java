package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextGameActivity extends AppCompatActivity {
    public RequestQueue newRQ;
    public StringRequest newSR;
    private String URL = "https://random-word-api.herokuapp.com/word?number=3";
    public String[] currWordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_text_game);
        getWords();
    }


    public void getWords() {
    //The code within this function for the API call has been derived from
    //https://www.geeksforgeeks.org/making-api-calls-using-volley-library-in-android/

        newRQ = Volley.newRequestQueue(this);
        newSR = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                response = response.replace("\"","");
                response= response.replace("[","");
                response = response.replace("]","");
                currWordList=response.split(",");
                displayWords();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TextGameActivity.this, "This game is currently unavailable",Toast.LENGTH_SHORT).show();
            }

        });
        newRQ.add(newSR);

    }

    public void displayWords(){
       try {
           Spinner firstSpinner = (Spinner) findViewById(R.id.spinner1);
           Spinner secondSpinner = (Spinner) findViewById(R.id.spinner2);
           Spinner thirdSpinner = (Spinner) findViewById(R.id.spinner3);

           //Code has been derived from: https://stackoverflow.com/questions/11920754/android-fill-spinner-from-java-code-programmatically
           ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                   TextGameActivity.this, android.R.layout.simple_spinner_item, currWordList
           );

           adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           firstSpinner.setAdapter(adapter);
           secondSpinner.setAdapter(adapter);
           thirdSpinner.setAdapter(adapter);
       }
       catch (Exception e){
           Log.w("TEXT GAME DISPLAYWORDS:",e);
        }
    }

    public void checkWords(){
        Spinner firstSpinner = (Spinner) findViewById(R.id.spinner1);
        Spinner secondSpinner = (Spinner) findViewById(R.id.spinner2);
        Spinner thirdSpinner = (Spinner) findViewById(R.id.spinner3);

        Arrays.sort(currWordList);
        String sort1 = currWordList[0];
        String sort2 = currWordList[1];
        String sort3 = currWordList[2];
        TextView spinnerText = (TextView)firstSpinner.getSelectedView();
        String spinner1Text = spinnerText.getText().toString();
        spinnerText = (TextView)secondSpinner.getSelectedView();
        String spinner2Text = spinnerText.getText().toString();
        spinnerText = (TextView)thirdSpinner.getSelectedView();
        String spinner3Text = spinnerText.getText().toString();


        if ((spinner1Text.equals(sort1))&&
                (spinner2Text.equals(sort2))&&
                (spinner3Text.equals(sort3))){
            Toast.makeText(TextGameActivity.this, "Correct!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(TextGameActivity.this, "That's not quite right",Toast.LENGTH_SHORT).show();
        }
    }

    public void reloadWords(View view){
        checkWords();
        getWords();
    }

    public void backBtnClick(View view){
        Intent backGM = new Intent(TextGameActivity.this, GamesMenu.class);
        backGM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backGM);

    }
}