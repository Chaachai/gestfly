package com.sharpinfo.sir.gestfly.action.conge;

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
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.CongeAdapter;
import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CongeListActivity extends AppCompatActivity {

    public static final String TAG = "CongeListActivity";

    private Context mContext = this;
    RecyclerView congeRecyclerView;
    CongeAdapter congeAdapter;

    List<Conge> conges;

    private void injecterGUI() {
        congeRecyclerView = findViewById(R.id.congeRecyclerView);
    }

    private void initAdapter() {
        conges = findCongeByUser(5L);

        congeAdapter = new CongeAdapter(conges);

        congeRecyclerView.setAdapter(congeAdapter);
        congeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        congeRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }

    private List<Conge> findCongeByUser(Long userid) {
        final List<Conge> res = new ArrayList<>();
        final List<TypeEtatDemande> typeEtatDemandes = getAllTypeEtatDemande();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Conge>> call = apiInterface.getCongesByUser(userid);
        call.enqueue(new Callback<List<Conge>>() {
            @Override
            public void onResponse(Call<List<Conge>> call, Response<List<Conge>> response) {
                Log.d(TAG, "Onresponse");
                List<Conge> cnges = response.body();
                if (cnges == null) {
                    Toast.makeText(CongeListActivity.this, "Vous n'avez aucune deamnde de congé ", Toast.LENGTH_SHORT).show();
                } else {
                    for (Conge conge : cnges) {
                        Log.d(TAG + "before", conge.getEtat().toString());
                        for (TypeEtatDemande type : typeEtatDemandes) {
                            if (conge.getEtat_id().equals(type.getId())) {
                                Log.d("hahowa l'etat",type.toString());
                                conge.setEtat(type);
                                Log.d( "ha lconge mora setEtat", conge.getEtat().toString());
                            }
                        }
                        Log.d(TAG, conge.toString());
                        res.add(conge);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Conge>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(CongeListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();

            }
        });
        return res;
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
                    Toast.makeText(CongeListActivity.this, "Il y'a eu une erreur dans le serveur , veuillez contacter un administrateur ", Toast.LENGTH_SHORT).show();
                } else {
                    typeEtatDemandes.addAll(types);
                }
            }

            @Override
            public void onFailure(Call<List<TypeEtatDemande>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(CongeListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();

            }
        });

        return typeEtatDemandes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conge_list);
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
                Dispacher.forward(CongeListActivity.this, CreateCongeActivity.class);
            }
        });
    }

}
