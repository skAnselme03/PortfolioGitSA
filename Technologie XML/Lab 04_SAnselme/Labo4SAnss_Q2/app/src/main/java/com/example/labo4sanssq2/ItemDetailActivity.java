package com.example.labo4sanssq2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Définit le contenu de l'activité à partir du fichier XML (activity_item_detail.xml)
        setContentView(R.layout.activity_item_detail);

        ImageView imageView = findViewById(R.id.imgViewHeroDetail);
        TextView textView = findViewById(R.id.heroLegendNameDetail);
        // Récupère l'Intent qui a lancé cette activité (contenant les données de l'item sélectionné)
        Intent intent = getIntent();
        // Récupère le nom de l'item à partir de l'Intent
        String itemName = intent.getStringExtra("itemName");
        // Récupère l'ID de l'image de l'item à partir de l'Intent (0 par défaut)
        int itemImage = intent.getIntExtra("itemImage", 0);

        // Affiche l'image de l'item dans l'ImageView
        imageView.setImageResource(itemImage);
        // Afficher les détails de l'item dans le TextView
        textView.setText(itemName);

        // Ajoutez un écouteur de clic à l'ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retournez à MainActivity en cliquant sur l'image
                finish();
            }
        });
    }
}