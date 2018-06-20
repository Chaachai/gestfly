package com.sharpinfo.sir.gestfly.action.salaire;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.conge.CreateCongeActivity;
import com.sharpinfo.sir.gestfly.action.menu.MenuTechnicienActivity;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemandeAvanceActivity extends AppCompatActivity {

    public static final String TAG = "DemandeAvanceActivity";

    private EditText moisAvance;
    private EditText messageAvance;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_avance);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        EditText year = findViewById(R.id.year);
        Log.d("tag", "year ========= " + year.getText());

        Calendar c = Calendar.getInstance();
        int thisYear = c.get(Calendar.YEAR) % 100;
        Log.d("tag", "this year ========= 20" + thisYear);
        year.setText("20" + thisYear);

        moisAvance = findViewById(R.id.mois_avance_create);
        messageAvance = findViewById(R.id.avance_msg);

        btn = findViewById(R.id.envoyer_demande_avance);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(moisAvance.getText()).equals("")) {
                    Toast.makeText(DemandeAvanceActivity.this, "Veuillez saisir un mois", Toast.LENGTH_SHORT).show();
                } else if (Integer.valueOf(String.valueOf(moisAvance.getText())) > 12 ||Integer.valueOf(String.valueOf(moisAvance.getText())) == 12) {
                    Toast.makeText(DemandeAvanceActivity.this, "Veuillez saisir une valeur correcte", Toast.LENGTH_SHORT).show();
                } else {
                    executeApiCall(Integer.valueOf(String.valueOf(moisAvance.getText())), String.valueOf(messageAvance.getText()));
                }
            }
        });
    }


    private void executeApiCall(Integer mois, String message) {
        User user = (User) Session.getAttribut("connectedUser");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sdf.format(new Date());

        Call<Integer> call = apiInterface.createDemandeSalaireAvance(date,message,mois,user.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    Toast.makeText(DemandeAvanceActivity.this, "Demande Avance salaire cree avec succes", Toast.LENGTH_SHORT).show();
                    Dispacher.forward(DemandeAvanceActivity.this, SalaireListActivity.class);
                    finish();
                } else {
                    Toast.makeText(DemandeAvanceActivity.this, "Une erreur est survenu lors de la creation", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(DemandeAvanceActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
