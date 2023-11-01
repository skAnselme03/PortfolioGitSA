package com.example.labo4sanssq2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    //liste d'image crée
    String[] names = {"Wonder Woman", "Spiderman","Batman","Captain America","Ironman"};
    //int [] images = {R.drawable.ic_launcher_background};
    int [] images = {R.drawable.wonderwoman, R.drawable.spiderman,
            R.drawable.batman, R.drawable.captainamerica, R.drawable.ironman};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///afficher les images
        listView = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter(names, images, this);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Passer l'item sélectionné à l'activité ItemDetailActivity
                String selectedItem = names[position];
                Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
                //intent.putExtra("selectedItem", selectedItem);
                intent.putExtra("itemName", names[position]);
                intent.putExtra("itemImage", images[position]);
                startActivity(intent);
            }
        });

    }
}