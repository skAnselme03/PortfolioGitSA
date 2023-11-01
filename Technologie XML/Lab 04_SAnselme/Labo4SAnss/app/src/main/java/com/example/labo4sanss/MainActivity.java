package com.example.labo4sanss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Définit le contenu de l'activité à partir du fichier XML activity_main.xml
        setContentView(R.layout.activity_main);
        // Recherche et assignation du bouton à partir de son ID dans le fichier XML
        btn = findViewById(R.id.btnActivity1);
        // Définition d'un écouteur de clic pour le bouton
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crée une nouvelle intention (Intent) pour démarrer une autre activité (MainActivity2)
                Intent i=new Intent(MainActivity.this, MainActivity2.class);
                // Démarre la nouvelle activité
                startActivity(i);
                // Enregistrer le passage dans le journal
                Log.i("Information", "passe à la deuxième activité");
            }
        });
    }
}