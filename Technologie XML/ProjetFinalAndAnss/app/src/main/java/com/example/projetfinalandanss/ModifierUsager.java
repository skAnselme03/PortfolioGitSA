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

import java.util.List;
import java.util.Random;

public class ModifierUsager extends Activity {

    private EditText editTextNomUsager;
    private EditText editTextNomAuComplet;
    private EditText editTextMotPasse;
    private RadioGroup radioGroupType;
    private UserDB userDB;
    private User selectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifier_usager);

        editTextNomUsager = findViewById(R.id.editTextNomUsager);
        editTextNomAuComplet = findViewById(R.id.editTextNomAuComplet);
        editTextMotPasse = findViewById(R.id.editTextMotPasse);
        radioGroupType = findViewById(R.id.radioGroupType);

        // Récupère le nom au complet de l'activité précédente
        String nomAuComplet = getIntent().getStringExtra("nomAuComplet");
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        textViewFullName.setText(nomAuComplet);

        // Initialise l'instance UserDB
        userDB = UserDB.getInstance();

        // Récupère l'usager sélectionné dans la vue Admin
        List<User> userList = userDB.getUserList();
        int selectedUserId = getIntent().getIntExtra("selectedUserId", -1);
        selectedUser = findUserById(userList, selectedUserId);

        if (selectedUser != null) {
            // Met les informations de l'usager sélectionné dans le formulaire
            editTextNomUsager.setText(selectedUser.getNomUsager());
            editTextNomAuComplet.setText(selectedUser.getNomAuComplet());
            editTextMotPasse.setText(selectedUser.getMotPasse());

            // Sélectionne le bouton radio selon le Type de l'usager
            if (selectedUser.getType().equals("Admin")) {
                radioGroupType.check(R.id.radioAdmin);
            } else if (selectedUser.getType().equals("Security")) {
                radioGroupType.check(R.id.radioSecurity);
            } else if (selectedUser.getType().equals("Visitor")) {
                radioGroupType.check(R.id.radioVisitor);
            }
        } else {
            // Erreur
            Toast.makeText(this, "ERREUR - Usager non sélectionné", Toast.LENGTH_SHORT).show();
        }


        // Récupère le bouton Logout du header
        Button buttonLogout = findViewById(R.id.buttonLogout);
        // Récupère le bouton Retour en arrière
        Button buttonRetour = findViewById(R.id.buttonRetour);
        // Récupère le bouton Modifier
        Button buttonModifier = findViewById(R.id.buttonModifier);

        // Retour à MainActivity si clic sur le bouton Déconnexion
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifierUsager.this, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Retour à VueAdmin si clic sur le bouton Retour
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifierUsager.this, VueAdmin.class);
                // Transmettre le nom dans le header
                intent.putExtra("nomAuComplet", nomAuComplet);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Action du bouton Modifier
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyUser();
            }
        });
    }

    private void modifyUser() {
        String type = getSelectedType();
        String nomUsager = editTextNomUsager.getText().toString();
        String nomAuComplet = editTextNomAuComplet.getText().toString();
        String motPasse = editTextMotPasse.getText().toString();

        // Génère un userID unique (voir méthode)
        int userId = generateUniqueUserId();

        // Met à jour les informations de l'utilisateur sélectionné
        selectedUser.setType(type);
        selectedUser.setNomUsager(nomUsager);
        selectedUser.setNomAuComplet(nomAuComplet);
        selectedUser.setMotPasse(motPasse);
        selectedUser.setUserId(userId);

        // Met à jour l'utilisateur dans UserDB
        userDB.updateUser(selectedUser);

        // Affiche un message
        Toast.makeText(this, "Usager modifié avec succès.", Toast.LENGTH_SHORT).show();

        // Termine l'activité
        finish();
    }

    // Méthode pour obtenir le type sélectionné dans le RadioGroup
    private String getSelectedType() {
        int selectedRadioButtonId = radioGroupType.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

        // Returns the text of the selected radio button
        return selectedRadioButton.getText().toString();
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

    private User findUserById(List<User> userList, int userId) {
        for (User user : userList) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null; // User not found
    }
}
