package com.sharpinfo.sir.gestfly.action.salaire;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.menu.MenuTechnicienActivity;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemandeAugmentationActivity extends AppCompatActivity {

    private EditText salaireActuel;
    private EditText montantAjouter;
    private EditText message;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_augmentation);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }


        salaireActuel = findViewById(R.id.salaire_actuel);
        montantAjouter = findViewById(R.id.montant_a_ajouter);
        message = findViewById(R.id.augmentation_msg);
        button = findViewById(R.id.envoyer_demande_augmentation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salaireActuel.getText().toString().equals("") || montantAjouter.getText().toString().equals("")) {
                    Toast.makeText(DemandeAugmentationActivity.this, "Veuillez remplir les informations", Toast.LENGTH_SHORT).show();
                } else {
                    BigDecimal salaire = new BigDecimal(String.valueOf(salaireActuel.getText()));
                    BigDecimal montantAjout = new BigDecimal(String.valueOf(montantAjouter.getText()));

                    executeApiCall(salaire, montantAjout, String.valueOf(message.getText()));
                }
            }
        });
    }


    private void executeApiCall(BigDecimal salaire, BigDecimal montantAjout, String message) {
        User user = (User) Session.getAttribut("connectedUser");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sdf.format(new Date());

        Call<Integer> call = apiInterface.createDemandeSalaireAugmentation(date, message, montantAjout, salaire, user.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    Toast.makeText(DemandeAugmentationActivity.this, "Demande Augmentation salaire cree avec succes", Toast.LENGTH_SHORT).show();
                    Dispacher.forward(DemandeAugmentationActivity.this, MenuTechnicienActivity.class);
                    finish();
                } else {
                    Toast.makeText(DemandeAugmentationActivity.this, "Une erreur est survenu lors de la creation", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(DemandeAugmentationActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
