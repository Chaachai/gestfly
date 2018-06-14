package com.sharpinfo.sir.gestfly.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Tache;

import java.util.List;

public class TacheSpinnerAdapter extends ArrayAdapter<Tache> {

    private Context context;
    private final List<Tache> taches;

    public TacheSpinnerAdapter(Context context, int textViewResourceId, List<Tache> taches) {
        super(context, textViewResourceId, taches);
        this.context = context;
        this.taches = taches;
    }

    @Override
    public int getCount() {
        return taches.size();
    }

    @Override
    public Tache getItem(int position) {
        return taches.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView tache = (TextView) super.getView(position, convertView, parent);
        tache.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        tache.setText(taches.get(position).getNom());

        // And finally return your dynamic (or custom) view for each spinner item
        return tache;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }


    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tache = (TextView) super.getDropDownView(position, convertView, parent);
        tache.setTextSize(15f);
        tache.setPadding(15,15,15,15);
        tache.setTextColor(Color.BLACK);
        tache.setText(taches.get(position).getNom());
        return tache;
    }
}
