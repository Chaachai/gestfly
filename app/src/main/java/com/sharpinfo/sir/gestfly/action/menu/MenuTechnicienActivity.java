package com.sharpinfo.sir.gestfly.action.menu;

import android.app.AlertDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.conge.CongeListActivity;
import com.sharpinfo.sir.gestfly.action.projet.ProjetListActivity;
import com.sharpinfo.sir.gestfly.action.rapport.CreerRapportActivity;
import com.sharpinfo.sir.gestfly.action.rapport.RapportListActivity;
import com.sharpinfo.sir.gestfly.action.salaire.SalaireListActivity;
import com.sharpinfo.sir.gestfly.action.tache.TacheListActivity;
import com.sharpinfo.sir.gestfly.action.user.LoginActivity;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuTechnicienActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView projetCard;
    private CardView tacheCard;
    private CardView congeCard;
    private CardView salaireCard;
    private CardView rapportCard;
    private CardView parametreCard;

    private Context mContext = this;
    AlertDialog alertDialog;
    Integer flagStart = 1;
    Integer flagEnd = 0;
    Integer flagProjet = 0;
    long startingTime;
    Date startingDate;
    long endingTime;
    Date endingDate;
    long totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_technicien);

        projetCard = findViewById(R.id.projetCardView);
        tacheCard = findViewById(R.id.tacheCardView);
        congeCard = findViewById(R.id.congeCardView);
        salaireCard = findViewById(R.id.salaireCardView);
        rapportCard = findViewById(R.id.rapprtCardView);

        projetCard.setOnClickListener(this);
        tacheCard.setOnClickListener(this);
        congeCard.setOnClickListener(this);
        salaireCard.setOnClickListener(this);
        rapportCard.setOnClickListener(this);

        Session.setAttribute(flagProjet, "flagProjet");

        Integer flag = (Integer) Session.getAttribut("flag");
        if (flag == 0) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            View mView = getLayoutInflater().inflate(R.layout.start_your_day_alert, null);

            ImageButton dismissButton = mView.findViewById(R.id.dismiss_start_day);


            builder.setView(mView);
            alertDialog = builder.create();

            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.projetCardView:
                Dispacher.forward(MenuTechnicienActivity.this, ProjetListActivity.class);
                break;
            case R.id.tacheCardView:
                Dispacher.forward(MenuTechnicienActivity.this, TacheListActivity.class);
                break;
            case R.id.congeCardView:
                Dispacher.forward(MenuTechnicienActivity.this, CongeListActivity.class);
                break;
            case R.id.salaireCardView:
                Dispacher.forward(MenuTechnicienActivity.this, SalaireListActivity.class);
                break;
            case R.id.rapprtCardView:
                Dispacher.forward(MenuTechnicienActivity.this, RapportListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Integer currentFlag = (Integer) Session.getAttribut("flag");
        switch (item.getItemId()) {
            case R.id.profile_option:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.start_day_option:
                if (currentFlag == 0) {
                    startDay();
                } else {
                    Toast.makeText(MenuTechnicienActivity.this, "Vous avez déjà commencé la journée !",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.end_day_option:
                if (currentFlag == 1) {
                    endDay();
                } else {
                    Toast.makeText(MenuTechnicienActivity.this, "Vous n'avez pas encore commencé votre journée !",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.change_password_option:
                Toast.makeText(this, "Change password", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.sign_out_option:
                Dispacher.forward(MenuTechnicienActivity.this, LoginActivity.class);
                endDay();
                Session.clear();
                finish();
                break;
            default:
                break;
        }
        return true;
    }

    public void startDayFromAlert(View view) {
        startDay();
        alertDialog.dismiss();
    }

    public void startDayLater(View view) {
        alertDialog.dismiss();
    }

    public void startDay() {
        Session.updateAttribute(flagStart, "flag");
        Toast.makeText(MenuTechnicienActivity.this, "votre journée a bien commencée",
                Toast.LENGTH_LONG).show();
        startingTime = System.currentTimeMillis();
        startingDate = new Date();
        Session.setAttribute(startingTime, "startTime");
        Session.setAttribute(startingDate, "startDate");
    }

    public void endDay() {
        Session.updateAttribute(flagEnd, "flag");
        User user = (User) Session.getAttribut("connectedUser");

        endingTime = System.currentTimeMillis();
        endingDate = new Date();

        Long sTime = (Long) Session.getAttribut("startTime");
        Date sDate = (Date) Session.getAttribut("startDate");

        totalTime = endingTime - sTime;

        int hours = (int) (totalTime / (1000 * 60 * 60));
        int mins = (int) ((totalTime / (1000 * 60)) % 60);
        long secs = (int) (totalTime / 1000) % 60;

        Log.d("tag", "Total dyal lwa9t hwa ======== " + hours + " hours " + mins + " minutes " + secs + " seconds");

        Session.delete("startTime");
        Session.delete("startDate");

        Toast.makeText(MenuTechnicienActivity.this, "Vous avez passé " + hours + " Heurs, " + mins + " minutes et " + secs + " seconds",
                Toast.LENGTH_LONG).show();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStartString = dateFormat.format(sDate);
        String dateEndString = dateFormat.format(endingDate);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Integer> call = apiInterface.createJournee(dateStartString, dateEndString, sTime, endingTime, totalTime, user.getId());
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
                Toast.makeText(MenuTechnicienActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


}
