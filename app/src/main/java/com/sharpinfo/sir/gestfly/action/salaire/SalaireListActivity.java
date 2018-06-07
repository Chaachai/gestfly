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
import android.view.View;
import android.widget.ImageButton;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.SalaireAdapter;
import com.sharpinfo.sir.gestfly.bean.DemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeDemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaireListActivity extends AppCompatActivity {

    private Context mContext = this;
    RecyclerView salaireRecyclerView;
    SalaireAdapter salaireAdapter;

    List<DemandeSalaire> salaires;

    private void injecterGUI() {
        salaireRecyclerView = findViewById(R.id.salaireRecyclerView);
    }

    private void initAdapter() {
        salaires = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

        // *************************************************************************************************

        TypeEtatDemande t1 = new TypeEtatDemande();
        TypeEtatDemande t2 = new TypeEtatDemande();
        TypeEtatDemande t3 = new TypeEtatDemande();

        t1.setLibelle("Accepté");
        t2.setLibelle("Refusé");
        t3.setLibelle("En cours");

        TypeDemandeSalaire ts1 = new TypeDemandeSalaire();
        TypeDemandeSalaire ts2 = new TypeDemandeSalaire();

        ts1.setType("Avance");
        ts2.setType("Augmentation");


        DemandeSalaire d1 = new DemandeSalaire();
        DemandeSalaire d2 = new DemandeSalaire();
        DemandeSalaire d3 = new DemandeSalaire();
        DemandeSalaire d4 = new DemandeSalaire();
        DemandeSalaire d5 = new DemandeSalaire();

        d1.setEtat(t1);
        d1.setType(ts1);
        d1.setMoisAvancer(new Date());

        d2.setEtat(t2);
        d2.setType(ts2);
        d2.setMontantAjouter(new BigDecimal(5000.0));

        d3.setEtat(t1);
        d3.setType(ts2);
        d3.setMontantAjouter(new BigDecimal(2000.0));

        d4.setEtat(t3);
        d4.setType(ts1);
        d4.setMoisAvancer(new Date());

        d5.setEtat(t3);
        d5.setType(ts2);
        d5.setMontantAjouter(new BigDecimal(2500.0));

        salaires.add(d1);
        salaires.add(d2);
        salaires.add(d3);
        salaires.add(d4);
        salaires.add(d5);

        // *************************************************************************************************

        salaireAdapter = new SalaireAdapter(salaires);

        salaireRecyclerView.setAdapter(salaireAdapter);
        salaireRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        salaireRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salaire_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        injecterGUI();
        initAdapter();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View mView = getLayoutInflater().inflate(R.layout.demande_salaire_popup, null);

                ImageButton dismissButton = mView.findViewById(R.id.dismiss_demande_salaire);


                builder.setView(mView);
                final AlertDialog alertDialog = builder.create();

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

    public void goToDemandeAvance(View view) {
        Dispacher.forward(SalaireListActivity.this, DemandeAvanceActivity.class);
    }


    public void goToDemandeAugmentation(View view) {
        Dispacher.forward(SalaireListActivity.this, DemandeAugmentationActivity.class);
    }
}
