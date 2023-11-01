package com.example.projetfinalandanss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AjoutUsager extends Activity {

    private RadioGroup radioGroup;
    private EditText editTextNomUsager;
    private EditText editTextNomAuComplet;
    private EditText editTextMotPasse;
    private UserDB userDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_usager);

        radioGroup = findViewById(R.id.radioGroupType);
        editTextNomUsager = findViewById(R.id.editTextNomUsager);
        editTextNomAuComplet = findViewById(R.id.editTextNomAuComplet);
        editTextMotPasse = findViewById(R.id.editTextMotPasse);

        // Récupère le nom au complet de l'activité précédente
        String nomAuComplet = getIntent().getStringExtra("nomAuComplet");
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        textViewFullName.setText(nomAuComplet);

        // Récupère le bouton Logout du header
        Button buttonLogout = findViewById(R.id.buttonLogout);
        // Récupère le bouton Ajouter
        Button buttonAjouter = findViewById(R.id.buttonAjouter);
        // Récupère le bouton Retour en arrière
        Button buttonRetour = findViewById(R.id.buttonRetour);

        // Initialise l'instance UserDB
        userDB = UserDB.getInstance();

        // Retour à MainActivity si clic sur le bouton Déconnexion
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AjoutUsager.this, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Retour à VueAdmin si clic sur le bouton Retour
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AjoutUsager.this, VueAdmin.class);
                // Transmettre le nom dans le header
                intent.putExtra("nomAuComplet", nomAuComplet);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Action du bouton Ajouter
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewUser();
            }
        });
    }

    private void addNewUser() {
        // Find the selected RadioButton
        RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());

        if (selectedRadioButton != null) {
            String type = selectedRadioButton.getText().toString();
            String nomUsager = editTextNomUsager.getText().toString();
            String nomAuComplet = editTextNomAuComplet.getText().toString();
            String motPasse = editTextMotPasse.getText().toString();

            // Génère un userID unique (voir méthode)
            int userId = generateUniqueUserId();

            // Crée un nouvel objet User
            User newUser = new User(type, nomUsager, nomAuComplet, motPasse, userId);

            // Ajoute le nouvel usager à UserDB
            userDB.addUser(newUser);

            // Affiche un message
            Toast.makeText(this, "Nouvel usager ajouté avec succès.", Toast.LENGTH_SHORT).show();

            // Clear the input fields
            editTextNomUsager.getText().clear();
            editTextNomAuComplet.getText().clear();
            editTextMotPasse.getText().clear();
            radioGroup.clearCheck(); // Clear the radio button selection
        } else {
            // Show a message if no RadioButton is selected
            Toast.makeText(this, "Veuillez sélectionner un type.", Toast.LENGTH_SHORT).show();
        }
    }


    // Méthode pour créer un nouvel UserID unique
    private int generateUniqueUserId() {
        Random random = new Random();
        int generatedUserId;

        // Fait la boucle jusqu'à qu'un userID unique soit généré
        while (true) {
            generatedUserId = random.nextInt(90000) + 10000; // Génère un nombre à 5 chiffres

            // Vérifie si le nombre généré existe déjà dans le fichier JSON
            boolean isUnique = true;

            for (User user : userDB.getUserList()) {
                if (user.getUserId() == generatedUserId) {
                    isUnique = false;
                    break;
                }
            }

            // Retourne le userID unique
            if (isUnique) {
                return generatedUserId;
            }
        }
    }
}