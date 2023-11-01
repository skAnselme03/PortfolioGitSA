package com.example.labo3sanss_q1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    //liste d'image crée
    String[] names = {"Daredevil","Punisher","Flash","Wonder Woman","Deadpool",
                      "Spiderman","Batman","Riddler","Black Panther","Avengers",
                      "Superman","Green Lantern","Fantastic Four","Captain America","Ironman"};
    //int [] images = {R.drawable.ic_launcher_background};
    int [] images = {R.drawable.daredevil, R.drawable.punisher, R.drawable.flash, 
                     R.drawable.wonderwoman,R.drawable.deadpool, R.drawable.spiderman, 
                     R.drawable.batman, R.drawable.riddler, R.drawable.blackpanther, 
                     R.drawable.avengers, R.drawable.superman, R.drawable.greenlantern, 
                     R.drawable.fantasticfour, R.drawable.captainamerica, R.drawable.ironman};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        CustomAdapter customAdapter = new CustomAdapter(names, images, this);
        gridView.setAdapter(customAdapter);
    }
}