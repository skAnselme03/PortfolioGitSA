package com.example.projetfinalandanss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

public class VueSecurity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_security);

        // Récupère le nom au complet de l'activité précédente
        String nomAuComplet = getIntent().getStringExtra("nomAuComplet");
        TextView textViewFullName = findViewById(R.id.textViewFullName);
        textViewFullName.setText(nomAuComplet);

        // Récupère le bouton Logout du header
        Button buttonLogout = findViewById(R.id.buttonLogout);

        GridView gridViewSecurity = findViewById(R.id.gridViewSecurity);

        // Retour si clic sur le bouton Déconnexion
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VueSecurity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Termine l'activité
            }
        });

        // Récupère la liste des usagers dans UserDB
        List<User> userList = UserDB.getInstance().getUserList();

        // Crée un adapter pour le GridView
        MyCustomAdapter adapter = new MyCustomAdapter(userList);
        gridViewSecurity.setAdapter(adapter);
    }
    private class MyCustomAdapter extends BaseAdapter {

        private List<User> data;

        MyCustomAdapter(List<User> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.grid_item_layout, parent, false);
            }

            TextView textViewType = convertView.findViewById(R.id.textViewType);
            TextView textViewNomUsager = convertView.findViewById(R.id.textViewNomUsager);
            TextView textViewNomAuComplet = convertView.findViewById(R.id.textViewNomAuComplet);
            TextView textViewMotPasse = convertView.findViewById(R.id.textViewMotPasse);
            TextView textViewUserID = convertView.findViewById(R.id.textViewUserID);

            User user = data.get(position);

            textViewType.setText("TYPE: " + user.getType());
            textViewNomUsager.setText("NOM USAGER: " + user.getNomUsager());
            textViewNomAuComplet.setText("NOM COMPLET: " + user.getNomAuComplet());
            textViewMotPasse.setText("MOT DE PASSE: " + user.getMotPasse());
            textViewUserID.setText("ID: " + user.getUserId());

            return convertView;
        }
    }
}
