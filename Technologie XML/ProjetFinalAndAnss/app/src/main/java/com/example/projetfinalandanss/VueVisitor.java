package com.example.projetfinalandanss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VueVisitor extends Activity {

    private UserDB userDB; // Accède aux informations des usagers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_visitor);

        // Initialize l'instance UserDB
        userDB = UserDB.getInstance();

        // Récupère le nom au complet de l'activité précédente
        String nomAuComplet = getIntent().getStringExtra("nomAuComplet");
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        textViewFullName.setText(nomAuComplet);

        // Récupère le bouton Logout du header
        Button buttonLogout = findViewById(R.id.buttonLogout);

        ListView listViewVisitor = findViewById(R.id.listViewVisitor);

        // Crée une liste des informations des usagers depuis UserDB
        List<String> userInfoList = new ArrayList<>();
        List<User> userList = userDB.getUserList();

        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String userInfo = (i + 1) + ". Nom Complet: " + user.getNomAuComplet();
            userInfoList.add(userInfo);
        }

        // Popule le ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userInfoList);
        listViewVisitor.setAdapter(adapter);

        // Retour si clic sur le bouton Déconnexion
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VueVisitor.this, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });
    }
}