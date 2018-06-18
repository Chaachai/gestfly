package com.sharpinfo.sir.gestfly.action.projet;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.menu.MenuTechnicienActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjetContentActivity extends AppCompatActivity {

    Long startingTime;
    Long endingTime;
    Long totalTime;
    Date startingDate;
    Date endingDate;
    Projet projet;
    Integer off = 0;
    Integer on = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_content);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Integer flagProjet = (Integer) Session.getAttribut("flagProjet");


        projet = (Projet) Session.getAttribut("clickedProjet");

        TextView nomProjet = findViewById(R.id.nom_projet_info_popup);
        TextView dateCreation = findViewById(R.id.dateCreation_projet_info_popup);
        TextView dateRealisation = findViewById(R.id.dateRealisation_projet_info_popup);
        TextView etatProjet = findViewById(R.id.etat_projet_info_popup);
        final TextView chefProjet = findViewById(R.id.chef_projet_info_popup);
        final Button projetStart = findViewById(R.id.btn_start_projet_info_popup);
        final Button projetFinish = findViewById(R.id.btn_finish_projet_info_popup);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateStringCreation = dateFormat.format(projet.getDateCreation());
        String dateStringRealisaton = dateFormat.format(projet.getDateDebut());

        dateCreation.setText(dateStringCreation);
        dateRealisation.setText(dateStringRealisaton);

        nomProjet.setText(projet.getNom());

        Long etat = projet.getEtat_id();
        Log.d("projetADapted", String.valueOf(etat));

        if (etat == 1) {
            etatProjet.setText("Validé");
        } else if (etat == 2) {
            etatProjet.setText("Non Validé");
        } else if (etat == 3) {
            etatProjet.setText("Soumission");
        } else if (etat == 4) {
            etatProjet.setText("Preparation de documents");
        } else {
            etatProjet.setText("erreur du serveur");
        }

        Long userId = projet.getUser_id();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.findUser(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d("projetADAPATER---------", user.toString());
                Log.d("projetADAPATER", response.body().toString());
                if (user == null) {
                    chefProjet.setText("Erreur du serveur");
                } else {
                    chefProjet.setText(String.format("%s %s", user.getLastName().toUpperCase(), user.getFirstName()));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        if (flagProjet == 0) {
            projetStart.setVisibility(View.VISIBLE);
            projetFinish.setVisibility(View.GONE);
        } else if (flagProjet == 1) {
            projetStart.setVisibility(View.GONE);
            projetFinish.setVisibility(View.VISIBLE);
        }

        projetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projetStart.setVisibility(View.GONE);
                projetFinish.setVisibility(View.VISIBLE);
                commencerTravail();
            }
        });

        projetFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projetFinish.setVisibility(View.GONE);
                projetStart.setVisibility(View.VISIBLE);
                finirTravail();
            }
        });

        Session.delete("clickedProjet");

    }

    private void commencerTravail() {
//        Projet p = (Projet) Session.getAttribut("clickedProjet");
        Session.setAttribute(projet, "sProjet");
        Session.updateAttribute(on, "flagProjet");
        startingTime = System.currentTimeMillis();
        startingDate = new Date();
        Session.setAttribute(startingTime, "tempsDebut");
        Session.setAttribute(startingDate, "DateDebut");
    }

    private void finirTravail() {
        Session.updateAttribute(off, "flagProjet");
        Projet p = (Projet) Session.getAttribut("sProjet");
        User user = (User) Session.getAttribut("connectedUser");

        endingTime = System.currentTimeMillis();
        endingDate = new Date();

        Long sTime = (Long) Session.getAttribut("tempsDebut");
        Date sDate = (Date) Session.getAttribut("DateDebut");

        totalTime = endingTime - sTime;

        int hours = (int) (totalTime / (1000 * 60 * 60));
        int mins = (int) ((totalTime / (1000 * 60)) % 60);
        long secs = (int) (totalTime / 1000) % 60;

        Log.d("tag", "Total dyal lwa9t hwa ======== " + hours + " hours " + mins + " minutes " + secs + " seconds");

        Session.delete("tempsDebut");
        Session.delete("DateDebut");
        Session.delete("sProjet");

        Toast.makeText(ProjetContentActivity.this, "Vous avez passé " + hours + " Heurs, " + mins + " minutes et " + secs + " seconds",
                Toast.LENGTH_LONG).show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStartString = dateFormat.format(sDate);
        String dateEndString = dateFormat.format(endingDate);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Integer> call = apiInterface.createTravailProjet(dateStartString, dateEndString, sTime, endingTime, totalTime, user.getId(), p.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    Log.d("tag", "journee cree avec succes");
                } else {
                    Log.d("tag", "Une erreur est survenu lors de la creation");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(ProjetContentActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
