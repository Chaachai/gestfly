package com.sharpinfo.sir.gestfly.action.rapport;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.helper.Session;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class RapportContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport_content);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Rapport rapport = (Rapport) Session.getAttribut("clickedRapport");

        TextView text = findViewById(R.id.text_rapport_content);
        TextView titre = findViewById(R.id.titre_rapport_content);
        TextView dateRapport = findViewById(R.id.date_rapport_content);
        TextView fullname = findViewById(R.id.user_rapport_content);
        TextView tache_projet = findViewById(R.id.projet_tache_titre);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = null;
        if (rapport != null) {
            dateString = dateFormat.format(rapport.getDate());
        }


        dateRapport.setText("La date : "+dateString);
        text.setText(rapport.getText());
        titre.setText(rapport.getTitre());
        fullname.setText("L'employé : " + rapport.getUser().getLastName().toUpperCase() + " " + rapport.getUser().getFirstName());
        if (rapport.getProjet().getNom() != null && rapport.getTache().getNom() == null) {
            tache_projet.setText("Pour le projet : " + rapport.getProjet().getNom());
        } else if (rapport.getProjet().getNom() == null && rapport.getTache().getNom() != null) {
            tache_projet.setText("Pour la tâche : " + rapport.getTache().getNom());
        } else {
            tache_projet.setText("");
        }

        Session.delete("clickedRapport");


    }
}
