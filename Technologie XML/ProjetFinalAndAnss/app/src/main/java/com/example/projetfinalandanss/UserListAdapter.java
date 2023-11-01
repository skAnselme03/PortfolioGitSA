package com.example.projetfinalandanss;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> userList;

    public UserListAdapter(Context context, List<User> userList) {
        super(context, R.layout.grid_item_layout, userList);
        this.context = context;
        this.userList = userList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.grid_item_layout, parent, false);
        }

        TextView textViewType = convertView.findViewById(R.id.textViewType);
        TextView textViewNomUsager = convertView.findViewById(R.id.textViewNomUsager);
        TextView textViewNomAuComplet = convertView.findViewById(R.id.textViewNomAuComplet);
        TextView textViewMotPasse = convertView.findViewById(R.id.textViewMotPasse);
        TextView textViewUserID = convertView.findViewById(R.id.textViewUserID);

        User user = userList.get(position);

        textViewType.setText("TYPE: " + user.getType());
        textViewNomUsager.setText("NOM USAGER: " + user.getNomUsager());
        textViewNomAuComplet.setText("NOM COMPLET: " + user.getNomAuComplet());
        textViewMotPasse.setText("MOT DE PASSE: " + user.getMotPasse());
        textViewUserID.setText("ID: " + user.getUserId());

        return convertView;
    }
}

