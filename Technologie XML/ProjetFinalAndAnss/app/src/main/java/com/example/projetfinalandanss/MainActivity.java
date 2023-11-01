package com.example.projetfinalandanss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.button);
        textViewMessage = findViewById(R.id.textViewMessage);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupère le nom usager et le mot de passe entrés par l'utilisateur
                String enteredUsername = editTextUsername.getText().toString();
                String enteredPassword = editTextPassword.getText().toString();

                // Va dans UserDB pour valider si l'usager existe
                User user = UserDB.getInstance().validateUser(enteredUsername, enteredPassword);

                if (user != null) {
                    Intent intent;
                    String userType = user.getType();

                    if ("Admin".equals(userType) || "admin".equals(userType)) {
                        // En avant vers VueAdmin
                        intent = new Intent(MainActivity.this, VueAdmin.class);
                    } else if ("Security".equals(userType) || "security".equals(userType)) {
                        // En avant vers VueSecurity
                        intent = new Intent(MainActivity.this, VueSecurity.class);
                    } else {
                        // En avant vers VueVisitor
                        intent = new Intent(MainActivity.this, VueVisitor.class);
                    }

                    // Transmet le nom complet aux autres activités
                    intent.putExtra("nomAuComplet", user.getNomAuComplet());
                    startActivity(intent);
                } else {
                    // Affiche le message d'erreur dans une petite fenêtre Toast
                    Toast.makeText(MainActivity.this, "Nom usager ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}