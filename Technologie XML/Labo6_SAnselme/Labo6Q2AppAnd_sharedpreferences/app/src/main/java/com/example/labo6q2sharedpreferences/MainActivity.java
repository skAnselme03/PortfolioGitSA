package com.example.labo6q2sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFS_KEY = "selected_color";
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        toolbar = findViewById(R.id.toolbar);

        // Récupérez les boutons
        Button btnVert = findViewById(R.id.btnvert);
        Button btnBleu = findViewById(R.id.btnbleu);
        Button btnJaune = findViewById(R.id.btnjaune);

        // Définir des listeners pour les boutons
        btnVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrergSetterBackCouleur(R.color.color_vert);
            }
        });

        btnBleu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrergSetterBackCouleur(R.color.color_bleu);
            }
        });

        btnJaune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enrergSetterBackCouleur(R.color.color_jaune);
            }
        });
    }

    private void enrergSetterBackCouleur(int colorResId) {
        // Enregistrez la couleur dans SharedPreferences
        sharedPreferences.edit().putInt(SHARED_PREFS_KEY, colorResId).apply();

        // Chargez la couleur à partir des ressources
        int color = getResources().getColor(colorResId);

        // Mettez à jour la couleur de fond de la Toolbar
        toolbar.setBackgroundColor(color);
    }
}