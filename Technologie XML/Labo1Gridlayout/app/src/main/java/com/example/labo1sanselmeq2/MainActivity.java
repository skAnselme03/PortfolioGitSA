package com.example.labo1sanselmeq2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {
    GridView gridView;

    //liste d'image crée
    String[] names = {"Yukon","Territoire du nord","Terreneuve & Labrador","Saskatchewan","Québec",
            "Ontario","Nunavut","Nouvelle-Ecosse","Nouveau-Brunswick","Manitoba",
            "Île-du-Prince-Édouard","Colombie-Britannique","Alberta"};
    //int [] images = {R.drawable.ic_launcher_background};
    int [] images = {R.drawable.yukon,R.drawable.tno,R.drawable.terreneuve_labrador,
            R.drawable.saskatchewan,R.drawable.quebec,R.drawable.ontario,
            R.drawable.nunavut,R.drawable.ne,R.drawable.nb,R.drawable.manitoba,
            R.drawable.ipe,R.drawable.cb,R.drawable.alberta};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.GridLayout1);

        CustomAdapter customAdapter = new CustomAdapter(names, images, this);
        gridView.setAdapter(customAdapter);
    }
}