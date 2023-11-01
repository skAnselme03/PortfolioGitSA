package com.example.projetfinalandanss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

public class VueAdmin extends AppCompatActivity {

    private ListView listViewUsers;
    private UserListAdapter userAdapter;
    private List<User> userList;
    private UserDB userDB;
    private EditText suppNomAuComplet;
    private EditText suppUserID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_admin);

        listViewUsers = findViewById(R.id.listViewUsers);
        suppNomAuComplet = findViewById(R.id.suppNomAuComplet);
        suppUserID = findViewById(R.id.suppUserID);

        userDB = UserDB.getInstance(); // Initialise l'instance UserDB
        loadUserList();

        // Récupère le nom au complet de l'activité précédente
        String nomAuComplet = getIntent().getStringExtra("nomAuComplet");
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        textViewFullName.setText(nomAuComplet);

        // Récupére les trois différents boutons
        Button btnAddUser = findViewById(R.id.btnAddUser);
        Button btnDeleteUser = findViewById(R.id.btnDeleteUser);
        Button buttonLogout = findViewById(R.id.buttonLogout);

        // Retour si clic sur le bouton Déconnexion
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VueAdmin.this, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Bouton pour se rendre à la page Ajouter utilisateur
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VueAdmin.this, AjoutUsager.class);
                // Transmettre le nom dans le header
                intent.putExtra("nomAuComplet", nomAuComplet);
                startActivity(intent);
            }
        });

        // Bouton pour supprimer un usager
        btnDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser();
            }
        });

        // Action pour modifier un usager si on clique sur lui dans la liste
        listViewUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Se rendre au formulaire de modification
                User selectedUser = userList.get(position);
                Intent intent = new Intent(VueAdmin.this, ModifierUsager.class);
                intent.putExtra("selectedUserId", selectedUser.getUserId());
                startActivity(intent);
            }
        });

        // Code Adapter pour la ListView...
        userAdapter = new UserListAdapter(this, userList);
        listViewUsers.setAdapter(userAdapter);
    }

    private void loadUserList() {
        userList = userDB.getUserList(); // Récupère la liste des utilisateurs dans UserDB
    }

    private void deleteUser() {
        String nomAuComplet = suppNomAuComplet.getText().toString().trim();
        String userIDStr = suppUserID.getText().toString().trim();

        if (!nomAuComplet.isEmpty() && !userIDStr.isEmpty()) {
            try {
                int userID = Integer.parseInt(userIDStr);
                boolean userDeleted = false;

                for (User user : userList) {
                    if (user.getNomAuComplet().equals(nomAuComplet) && user.getUserId() == userID) {
                        userDB.deleteUser(user.getUserId()); // Supprime de UserDB
                        userDeleted = true;
                        userList.remove(user); // Retire de la liste locale
                        break;
                    }
                }

                if (userDeleted) {
                    userAdapter.notifyDataSetChanged(); // Code pour l'adapteur...
                    suppNomAuComplet.setText("");
                    suppUserID.setText("");
                    showToast("Usager supprimé correctement");
                } else {
                    showToast("Usager non trouvé ou informations incorrectes");
                }
            } catch (NumberFormatException e) {
                showToast("Veuillez entrer un UserID valide");
            }
        } else {
            showToast("Veuillez entrer un nom complet et un UserID");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}