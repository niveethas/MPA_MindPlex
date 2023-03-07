package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class numberGameActivity extends AppCompatActivity {
    private int num1;
    private int num2;
    private int num3;
    int largest;
    public Toast outcomeToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Theme.Night == true){
            this.setTheme(R.style.Theme_Coursework_Mindplex_Night);
        }
        else{
            this.setTheme(R.style.Theme_Coursework_Mindplex);

        }
        setContentView(R.layout.activity_number_game);
        displayNumbers();
        outcomeToast= Toast.makeText( numberGameActivity.this  , "" , Toast.LENGTH_SHORT );
    }

    private void displayNumbers(){
        Random r = new Random();
        num1 = r.nextInt(20);
        num2 = r.nextInt(20);
        num3 = r.nextInt(20);
        while (num2==num1 || num3 ==num1 || num3==num2){
            num2 = r.nextInt(20);
            num3 = r.nextInt(20);
        }
        Button left = (Button) findViewById(R.id.numLeft);
        left.setText("" + num1);
        Button right = (Button) findViewById(R.id.numMiddle);
        right.setText("" + num2);
        Button middle = (Button) findViewById(R.id.numRight);
        middle.setText("" + num3);
    }

    private void check(int a, int b, int c, String button) {
        if(a>=b && a>=c)   {
            largest = a;

        } else if (b>=a && b>=c){
            largest = b;
        }else{
            largest = c;
        }
        if (button.equals("left") && largest == a){
            outcomeToast.setText("You clicked " + largest + ", which is the largest number!");
            outcomeToast.show();
        }
        else if (button.equals("middle") && largest == b){
            outcomeToast.setText("You clicked " + largest + ", which is the largest number!");
            outcomeToast.show();
        }else if (button.equals("right") && largest == c){
            outcomeToast.setText("You clicked " + largest + ", which is the largest number!");
            outcomeToast.show();
        } else {
            outcomeToast.setText("Keep Going!");
            outcomeToast.show();
        }

        displayNumbers();
    }

    public void backBtnClick(View view){
        Intent backGM = new Intent(numberGameActivity.this, GamesMenu.class);
        backGM.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(backGM);

    }

    public void clickNumLeft(View view) {
        check(num1,num2,num3, "left");
    }
    public void clickNumRight(View view) {
        check(num1,num2,num3, "right");
    }
    public void clickNumMiddle(View view) {
        check(num1,num2,num3, "middle");
    }
}