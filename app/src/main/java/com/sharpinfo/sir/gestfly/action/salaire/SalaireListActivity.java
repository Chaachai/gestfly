package com.sharpinfo.sir.gestfly.action.salaire;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.conge.CongeListActivity;
import com.sharpinfo.sir.gestfly.adapter.SalaireAdapter;
import com.sharpinfo.sir.gestfly.bean.DemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeDemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaireListActivity extends AppCompatActivity {
    public static final String TAG = "SalaireListActivity";


    private Context mContext = this;
    RecyclerView salaireRecyclerView;
    SalaireAdapter salaireAdapter;
    AlertDialog alertDialog;

    List<DemandeSalaire> salaires;

    private void injecterGUI() {
        salaireRecyclerView = findViewById(R.id.salaireRecyclerView);
    }

    private void initAdapter() {
//        salaires = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

//        // *************************************************************************************************
//
//        TypeEtatDemande t1 = new TypeEtatDemande();
//        TypeEtatDemande t2 = new TypeEtatDemande();
//        TypeEtatDemande t3 = new TypeEtatDemande();
//
//        t1.setLibelle("Accepté");
//        t2.setLibelle("Refusé");
//        t3.setLibelle("En cours");
//
//        TypeDemandeSalaire ts1 = new TypeDemandeSalaire();
//        TypeDemandeSalaire ts2 = new TypeDemandeSalaire();
//
//        ts1.setType("Avance");
//        ts2.setType("Augmentation");
//
//
//        DemandeSalaire d1 = new DemandeSalaire();
//        DemandeSalaire d2 = new DemandeSalaire();
//        DemandeSalaire d3 = new DemandeSalaire();
//        DemandeSalaire d4 = new DemandeSalaire();
//        DemandeSalaire d5 = new DemandeSalaire();
//
//        d1.setEtat(t1);
//        d1.setType(ts1);
//        d1.setMoisAvancer(new Date());
//
//        d2.setEtat(t2);
//        d2.setType(ts2);
//        d2.setMontantAjouter(new BigDecimal(5000.0));
//
//        d3.setEtat(t1);
//        d3.setType(ts2);
//        d3.setMontantAjouter(new BigDecimal(2000.0));
//
//        d4.setEtat(t3);
//        d4.setType(ts1);
//        d4.setMoisAvancer(new Date());
//
//        d5.setEtat(t3);
//        d5.setType(ts2);
//        d5.setMontantAjouter(new BigDecimal(2500.0));
//
//        salaires.add(d1);
//        salaires.add(d2);
//        salaires.add(d3);
//        salaires.add(d4);
//        salaires.add(d5);
//
//        // *************************************************************************************************


        salaires = findSalairesByUser(5L);
        salaireAdapter = new SalaireAdapter(salaires);

        salaireRecyclerView.setAdapter(salaireAdapter);
        salaireRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        salaireRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }

    private List<DemandeSalaire> findSalairesByUser(Long userId) {
        final List<DemandeSalaire> res = new ArrayList<>();
        final List<TypeEtatDemande> typeEtatDemandes = getAllTypeEtatDemande();


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<DemandeSalaire>> call = apiInterface.getDemandesSalaireByUser(userId);
        call.enqueue(new Callback<List<DemandeSalaire>>() {
            @Override
            public void onResponse(Call<List<DemandeSalaire>> call, Response<List<DemandeSalaire>> response) {
                List<DemandeSalaire> dmndesSal = response.body();
                if (dmndesSal == null) {
                    Toast.makeText(SalaireListActivity.this, "Vous n'avez aucune demande de salaire ", Toast.LENGTH_SHORT).show();
                } else {
                    for (DemandeSalaire demandeSalaire : dmndesSal) {

                        for (TypeEtatDemande type : typeEtatDemandes) {
                            if (demandeSalaire.getEtat_id().equals(type.getId())) {
                                Log.d("hahowa l'etat", type.toString());
                                demandeSalaire.setEtat(type);
                                Log.d("ha lconge mora setEtat", demandeSalaire.getEtat().toString());
                            }
                        }
                        Log.d(TAG, demandeSalaire.toString());
                        res.add(demandeSalaire);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DemandeSalaire>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(SalaireListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();

            }
        });

        return res;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salaire_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        injecterGUI();
        initAdapter();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View mView = getLayoutInflater().inflate(R.layout.demande_salaire_popup, null);

                ImageButton dismissButton = mView.findViewById(R.id.dismiss_demande_salaire);


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
        });
    }

    private List<TypeEtatDemande> getAllTypeEtatDemande() {
        final List<TypeEtatDemande> typeEtatDemandes = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<TypeEtatDemande>> call = apiInterface.getAllTypeEtatDemandes();
        call.enqueue(new Callback<List<TypeEtatDemande>>() {
            @Override
            public void onResponse(Call<List<TypeEtatDemande>> call, Response<List<TypeEtatDemande>> response) {
                Log.d(TAG, "Onresponse");
                List<TypeEtatDemande> types = response.body();
                if (types == null) {
                    Toast.makeText(SalaireListActivity.this, "Il y'a eu une erreur dans le serveur , veuillez contacter un administrateur ", Toast.LENGTH_SHORT).show();
                } else {
                    typeEtatDemandes.addAll(types);
                }
            }

            @Override
            public void onFailure(Call<List<TypeEtatDemande>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(SalaireListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();

            }
        });

        return typeEtatDemandes;
    }

    private List<TypeDemandeSalaire> getAllTypeDemandeSalaire() {
        final List<TypeDemandeSalaire> typeDemandeSalaires = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<TypeDemandeSalaire>> call = apiInterface.getAllTypeDemandeSalaire();
        call.enqueue(new Callback<List<TypeDemandeSalaire>>() {
            @Override
            public void onResponse(Call<List<TypeDemandeSalaire>> call, Response<List<TypeDemandeSalaire>> response) {
                Log.d(TAG, "Onresponse");
                List<TypeDemandeSalaire> types = response.body();
                if (types == null) {
                    Toast.makeText(SalaireListActivity.this, "Il y'a eu une erreur dans le serveur , veuillez contacter un administrateur ", Toast.LENGTH_SHORT).show();
                } else {
                    typeDemandeSalaires.addAll(types);
                }
            }

            @Override
            public void onFailure(Call<List<TypeDemandeSalaire>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(SalaireListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();

            }
        });
        return typeDemandeSalaires;
    }

    public void goToDemandeAvance(View view) {
        Dispacher.forward(SalaireListActivity.this, DemandeAvanceActivity.class);
        alertDialog.dismiss();
    }


    public void goToDemandeAugmentation(View view) {
        Dispacher.forward(SalaireListActivity.this, DemandeAugmentationActivity.class);
        alertDialog.dismiss();
    }
}
