package com.example.labo3sanss_q3;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNomUsager, editTextMotDePasse;
    private Button boutonCliquer;
    private TextView textViewResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNomUsager = findViewById(R.id.editTextNomUsager);
        editTextMotDePasse = findViewById(R.id.editTextMotDePasse);
        boutonCliquer = findViewById(R.id.boutonCliquer);
        textViewResultat = findViewById(R.id.textViewResultat);

        boutonCliquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer le nom d'utilisateur et le mot de passe saisis
                String nomUsager = editTextNomUsager.getText().toString();
                String motDePasse = editTextMotDePasse.getText().toString();

                // Vérifier si le mot de passe a au moins 8 caractères
                //if (motDePasse.length() < 8) {
                    //editTextMotDePasse.setError("Le mot de passe doit avoir au moins 8 caractères");
                  //  return; // Sortir de la méthode onClick si le mot de passe est invalide
                //}

                // Vérifier les conditions
                if (nomUsager.toLowerCase().equals("bob") && motDePasse.equals("bob1234")) {
                    textViewResultat.setText("Bienvenue: " + nomUsager);
                } else {
                    textViewResultat.setText("Mot de Passe ou nom d'utilisateur invalide");
                }
            }
        });
    }
}