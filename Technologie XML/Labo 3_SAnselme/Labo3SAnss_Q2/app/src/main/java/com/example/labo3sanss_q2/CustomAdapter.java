package com.example.labo3sanss_q2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private String[] imageName;
    private int[] imagesPhoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(String[] imageName, int[] imagesPhoto, Context context) {
        this.imageName = imageName;
        this.imagesPhoto = imagesPhoto;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imageName.length;
    }

    @Override
    public Object getItem(int i) {
        return imageName[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_items, viewGroup, false);
        }

        ImageView imageView = view.findViewById(R.id.imgViewHero);
        TextView pvName = view.findViewById(R.id.heroLegendName);

        // Set image and text data here
        // Assuming you have an array of image resource IDs named 'imageResources'
        pvName.setText(this.imageName[i]);
        imageView.setImageResource(this.imagesPhoto[i]);

        return view;
    }
}
