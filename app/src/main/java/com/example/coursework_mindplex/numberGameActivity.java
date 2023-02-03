package com.example.coursework_mindplex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class numberGameActivity extends AppCompatActivity {
    private int num1; // the numbers on the left and right buttons
    private int num2;
    private int num3;
    int largest;

    public void clickNumLeft(View view) {
        check(num1,num2,num3, "left");
    }
    public void clickNumRight(View view) {
        check(num1,num2,num3, "right");
    }
    public void clickNumMiddle(View view) {
        check(num1,num2,num3, "middle");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);
        roll();
    }

    private void roll(){
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
            Toast.makeText(this, "You clicked " + largest + ", which is the largest number!", Toast.LENGTH_SHORT).show();
        }
        else if (button.equals("middle") && largest == b){
            Toast.makeText(this, "You clicked " + largest + ", which is the largest number!", Toast.LENGTH_SHORT).show();
        }else if (button.equals("right") && largest == c){
            Toast.makeText(this, "You clicked " + largest + ", which is the largest number!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Keep Going!", Toast.LENGTH_SHORT).show();

        }

        roll();
    }
}